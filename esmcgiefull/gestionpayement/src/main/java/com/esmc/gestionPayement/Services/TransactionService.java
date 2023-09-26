package com.esmc.gestionPayement.Services;

import com.esmc.gestionPayement.Repositories.CorisRepository;
import com.esmc.gestionPayement.Repositories.TransactionRepo;
import com.esmc.gestionPayement.ServicesInterface.TransactionServiceInterface;
import com.esmc.gestionPayement.config.CorisBankRequestOptions;
import com.esmc.gestionPayement.entities.Transactions;
import com.esmc.gestionPayement.entities.coris.Coris;
import com.esmc.gestionPayement.entities.coris.CorisOutput;
import com.esmc.gestionPayement.feign.TeRestClient;
import com.esmc.gestionPayement.inputs.*;
import com.esmcgie.models.RequestOptions;
import com.esmcgie.requests.ClientInfo;
import com.esmcgie.requests.OtpInfo;
import com.esmcgie.services.CorisService;
import com.esmcgie.services.impls.CorisServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import input.LoginCinetPayRequest;
import net.minidev.json.JSONObject;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.esmc.gestionPayement.utils.ConfigUtils.CORIS_API_URL;
import static com.esmcgie.utils.JavaUtils.CORIS_BASE_URL;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class TransactionService implements TransactionServiceInterface {
    @Autowired
    private TransactionRepo transactionRepository;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private TeRestClient teRestClient;


//    @Autowired
//    private SmsApiRestClient smsApiRestClient;
@Autowired
private CorisRepository corisRepository;
    @Override
    public List<Transactions> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transactions getTransactionsById(Long id) {
        return transactionRepository.getTransactionsById(id);
    }

    @Override
    public Transactions getTransactionsByReference(String reference) {

        return  transactionRepository.getTransactionsByReference(reference);
    }

    @Override
    public Transactions createTransaction(TransactionInput data) {
        String origin = "TMoney";
        String source ="BPSD V";
        Transactions transactions = new Transactions();
        transactions.setOrigin(origin);
        transactions.setData(data.toString());
        transactions.setSource(source);
        transactions.setMontant(data.getData().getAmount());
        transactions.setReference(data.getData().getOperator_id());

        Transactions t = transactionRepository.save(transactions);
        return t;
    }

    @Override
    public Transactions createTeTransaction(TransactionInput data, Long idTe, Long idKsu) {
        String origin = "CinetPay";
        String source ="BPSD V";
        Transactions transactions = new Transactions();
        transactions.setOrigin(origin);
        transactions.setIdKsu(idKsu);
        transactions.setIdTe(idTe);
        //transactions.setTransactionId(data.getTransactionId());
        transactions.setData(data.toString());
        transactions.setSource(source);
        transactions.setMontant(data.getData().getAmount());
        transactions.setReference(data.getData().getOperator_id());
        return transactionRepository.save(transactions);
    }
    @Override
    public List<Transactions> getTransactionByIdTe(Long idTe,boolean status){
        return transactionRepository.getTransactionByIdTe(idTe,status);
    }

    @Override
    public Transactions getTransactionByIdKsuAndIdTe(Long idKsu, Long idTe) {
        return transactionRepository.getTransactionByIdKsuAndIdTe(idKsu, idTe);
    }

    @Override
    public Transactions createTansApro(Double montant, Long idKsu, Long idTe) {

        Double montantTotal = transactionRepository.sommeMev(idKsu, idTe);
        Transactions t = new Transactions();
        t.setMontant(-montant);
        t.setIdKsu(idKsu);
        t.setIdTe(idTe);
        t.setReference(null);
        t.setUsed(true);
        Transactions trans = transactionRepository.save(t);

        return trans;
    }

    @Override
    public Double sommeMev(Long idKsu, Long idTe) {
        return transactionRepository.sommeMev(idKsu, idTe);
    }





    //******************************* CORIS BANK SERVICE *********************************************************************************************

    // REST TEMPLATE TO COMMUNICATE WITH EXTERNAL URL
    public RestTemplate createRestTemplate() throws Exception {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }

    // THIS FUCNTION TO HASH YOUR REQUEST PARAMETERS
    public String wonderHashFunction(String originalString){
        String sha256hex = Hashing.sha256().hashString(originalString, StandardCharsets.UTF_8).toString();
        return sha256hex;
    }

    // FIRST STEP OF GOODS PAYEMENT : ASK TO RECEIVE AN OTP CODE TO PROCESS YOUR PAIEMENT

    @Override
    public Object sendOTPCode(DemandeOtpInput data) throws Exception {

        RestTemplate restTemplate = createRestTemplate();

        String clientId = "ESMCGIE";
        String clientSecret = "$2a$10$vIIt8T2W5XE3k8PFUXT3dO5XQY2sr5eME3XWErS73WajkJxpz75x2";

        Map<String, String> map = new HashMap<>();
        map.put("countryCode", data.getCountryCode());
        map.put("phone", data.getPhone());
        ObjectMapper mapper = new ObjectMapper();
        DemandeOtpInput info = mapper.convertValue(map, DemandeOtpInput.class);
        System.out.println(info);


        ObjectMapper resultmapper = new ObjectMapper();
        String resultOfTheRequest = "null";
        String hashParam = wonderHashFunction(info.getCountryCode() + "" + info.getPhone() + "" + clientSecret);
        String url = CORIS_API_URL + "send-code-otp?codePays=" +data.getCountryCode()+"&telephone="+data.getPhone();

        HttpHeaders headers = new HttpHeaders();
        headers.set("hashParam", hashParam);
        headers.set("clientId", clientId);
        headers.setContentType(APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        System.out.println("REQUEST HEADER  : "+entity);
        System.out.println("REQUEST URL  : "+url);


        try {
            resultOfTheRequest = restTemplate.postForObject(url, entity, String.class);
            System.out.println("PART 1  : Result of the request  "+resultOfTheRequest);
            Object object = mapper.readValue(resultOfTheRequest, Object.class);
            return object;
//            return resultOfTheRequest;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }



    // SECOND AND LAST STEP OF GOODS PAYEMENT : PROCESS TO  YOUR PAIEMENT

    @Override
    public Object makePaymentForGoods(PayementBienInput data) throws Exception {

        RestTemplate restTemplate = createRestTemplate();

        String clientId = "ESMCGIE";
        String clientSecret = "$2a$10$vIIt8T2W5XE3k8PFUXT3dO5XQY2sr5eME3XWErS73WajkJxpz75x2";
        Map<String, Object> map = new HashMap<>();
        map.put("countryCode", data.getCountryCode());
        map.put("phone", data.getPhone());
        map.put("codePv", data.getCodePv());
        map.put("amount", data.getAmount());
        map.put("codeOTP",data.getCodeOTP());
        ObjectMapper mapper = new ObjectMapper();
        PayementBienInput info = mapper.convertValue(map, PayementBienInput.class);
        System.out.println(info);

//        String codePv =   "0074267744";
        ObjectMapper resultmapper = new ObjectMapper();
        String resultOfTheRequest = "null";

        String hashParam = wonderHashFunction(info.getCountryCode() + "" + info.getPhone() + "" + "" + info.getCodePv() + "" + info.getAmount() + "" + info.getCodeOTP() + "" + clientSecret);

        String url = CORIS_API_URL + "operations/paiement-bien?codePays=" +data.getCountryCode()+"&telephone="+data.getPhone()+"&codePv="+data.getCodePv()+"&montant="+data.getAmount()+"&codeOTP="+data.getCodeOTP();

        HttpHeaders headers = new HttpHeaders();
        headers.set("hashParam", hashParam);
        headers.set("clientId", clientId);
        headers.setContentType(APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<>(headers);
        System.out.println(" REQUEST HEADER : "+entity);
        System.out.println("REQUEST URL  : "+url);

        try {
            CorisOutput rt = restTemplate.postForObject(url, entity, CorisOutput.class);
            System.out.println(" PART 2 : result of the goods payement   "+rt);
            Object object = mapper.readValue(resultOfTheRequest, Object.class);

//
//            MessageSms messageSms=new MessageSms();
//
//
//            messageSms.setMessage("fuck you");
//            messageSms.setClientId("rf");
//            messageSms.setApiKey("5166");
//            messageSms.setMobileNumbers(info.getCountryCode()+info.getPhone());

            // smsApiRestClient.sendSms(messageSms);







            Coris coris = new Coris() ;
            coris.setMontant(data.getAmount());
            coris.setTransactionId(rt.getTransactionId());
            coris.setCodeRef("ref"+rt.getTransactionId());
            coris.setIdKsu(data.getIdKsu());
            coris.setIdTe(data.getIdTe());

            System.out.println(" sauvegarde de l'objet coris    "+coris);
            corisRepository.save(coris);




            return rt;


        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }

    @Override
    public String makeInternetPayement(PayementInternetInput data) throws Exception {
        RestTemplate restTemplate = createRestTemplate();
        String clientId = "ESMCGIE";
        String clientSecret = "$2a$10$vIIt8T2W5XE3k8PFUXT3dO5XQY2sr5eME3XWErS73WajkJxpz75x2";
        Map<String, String> map = new HashMap<>();
        map.put("countryCode", data.getCountryCode());
        map.put("phone", data.getPhone());
        map.put("codePv", data.getCodePv());
        map.put("withdrawalCode", data.getWithdrawalCode());
        map.put("amount",data.getAmount());
        map.put("isMontant",data.getIsMontant());
        ObjectMapper mapper = new ObjectMapper();
        PayementInternetInput info = mapper.convertValue(map, PayementInternetInput.class);
        System.out.println(info);

        String resultOfTheRequest = null;

        String hashParam = wonderHashFunction(info.getCountryCode() + "" + info.getPhone() + "" + "" + info.getCodePv() + "" + info.getWithdrawalCode() + "" + info.getAmount() + "" + info.getIsMontant() + ""  + clientSecret);

        String url = CORIS_API_URL + "operations/paiement-internet?codePays=" +data.getCountryCode()+"&telephone="+data.getPhone()+"&codePv="+data.getCodePv()+"&codeRetrait="+data.getWithdrawalCode()+"&montant="+data.getAmount();

        HttpHeaders headers = new HttpHeaders();
        headers.set("hashParam", hashParam);
        headers.set("clientId", clientId);
        headers.setContentType(APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        System.out.println(" REQUEST HEADER : "+entity);
        System.out.println("REQUEST URL  : "+url);

        try {
            resultOfTheRequest = restTemplate.postForObject(url, entity, String.class);
            System.out.println(" PART 2 : result of the internet payement   "+resultOfTheRequest);
            return resultOfTheRequest;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }


    // USE THIS SERVICE TO GET INFORMATION ABOUT THE CLIENT
    @Override
    public String recoverClientInformation(String countryCode , String phone ) throws Exception {

        RestTemplate restTemplate = createRestTemplate();

        String clientId = "ESMCGIE";
        String clientSecret = "$2a$10$vIIt8T2W5XE3k8PFUXT3dO5XQY2sr5eME3XWErS73WajkJxpz75x2";
        Map<String, String> map = new HashMap<>();
        map.put("countryCode",countryCode );
        map.put("phone",  phone);
        ObjectMapper mapper = new ObjectMapper();
        ClientInfosInput info = mapper.convertValue(map, ClientInfosInput.class);
        System.out.println(info);

        String resultOfTheRequest = null;

        String hashParam = wonderHashFunction(info.getCountryCode() + "" + info.getPhone()  + clientSecret);

        String url = CORIS_API_URL + "infos-client?codePays="+countryCode+"&telephone="+phone;

        HttpHeaders headers = new HttpHeaders();
        headers.set("hashParam", hashParam);
        headers.set("clientId", clientId);
        headers.setContentType(APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);


        System.out.println(" REQUEST HEADER : "+entity);
        System.out.println("REQUEST URL  : "+url);

        try {
//            resultOfTheRequest = restTemplate.getForObject(url, String.class);
            ResponseEntity<String> response = restTemplate.exchange(url, GET ,entity, String.class);
            ObjectMapper mappers = new ObjectMapper();


//            System.out.println("  CLIENT INFORMATION   "+response);
            return mappers.convertValue(response.getBody(), Object.class).toString();

        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }

    }

    @Override
    public Object checkTransactionStatus(String codeOperation ) throws Exception {
        RestTemplate restTemplate = createRestTemplate();
        String clientId = "ESMCGIE";
        String clientSecret = "$2a$10$vIIt8T2W5XE3k8PFUXT3dO5XQY2sr5eME3XWErS73WajkJxpz75x2";
        Map<String, Object> map = new HashMap<>();
        map.put("codeOperation",codeOperation );
        ObjectMapper mapper = new ObjectMapper();
        CheckCorisTransationInput info = mapper.convertValue(map, CheckCorisTransationInput.class);
        System.out.println(info);

        ObjectMapper resultmapper = new ObjectMapper();
        String resultOfTheRequest = "null";


        String hashParam = wonderHashFunction(  info.getCodeOperation()  + clientSecret);

        String url = CORIS_API_URL + "operations/transaction-status?codeOperation="+codeOperation;

        HttpHeaders headers = new HttpHeaders();
        headers.set("hashParam", hashParam);
        headers.set("clientId", clientId);
        headers.setContentType(APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        System.out.println(" REQUEST HEADER : "+entity);
        System.out.println("REQUEST URL  : "+url);
        try {

            Coris coris=corisRepository.findByCodeRef(codeOperation);
            coris.setStatus(false);
            corisRepository.save(coris);
            System.out.println("mon coris"+coris);

            if(coris==null){
                return  null;
            }
            CorisOutput rt  = restTemplate.exchange(url,GET, entity, CorisOutput.class).getBody();

//            CorisOutput rt = restTemplate.postForObject(url, entity, CorisOutput.class);
            System.out.println(" corisoutput transactions check   "+rt);


            ResponseEntity<String> response = restTemplate.exchange(url, GET ,entity, String.class);
            ObjectMapper mappers = new ObjectMapper();
            System.out.println(" CORIS TRANSACTION CHECK"+response);
            Object object = mapper.readValue(resultOfTheRequest, Object.class);




            if(rt.getMessage().equals("PAYE")){
                System.out.println(" le rt dans if   "+rt);
                System.out.println(" le coris dans if   "+coris);
                Transactions transactions=new Transactions();

                transactions.setData(coris.toString());
                transactions.setTransactionId(coris.getTransactionId());
                transactions.setIdTe(coris.getIdTe());
                transactions.setIdKsu(coris.getIdKsu());
                transactions.setMontant(Double.valueOf(coris.getMontant()));
                transactions.setReference(coris.getCodeRef());


                createTransactionCoris(transactions);
                System.out.println(" le transaction est créeer   "+transactions);

            }else{
                System.out.println(" le transaction est ne peut etre creer   ");
            }
            return rt;
//            return mappers.convertValue(response.getBody(), Object.class).toString();
//            return resultOfTheRequest;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }

    @Override
    public Transactions createTransactionCoris(Transactions data) {

        Transactions transactions=new Transactions();
        String origin = "Coris";
        String source ="BPSD V";

        transactions.setReference(data.getReference());
        transactions.setTransactionId(data.getTransactionId());
        transactions.setMontant(Double.valueOf(data.getMontant()));
        transactions.setIdTe(data.getIdTe());
//        transactions.setData(""+data.getTransactionId()+""+data.getCountryCode()+""+data.getPhone()+""
//                +data.getMontant()+""+data.getReference());
        transactions.setData(""+data.getTransactionId()+""+data.getMontant());
        transactions.setIdKsu(data.getIdKsu());

        transactions.setSource(source);
        transactions.setOrigin(origin);

        Transactions transactions1= transactionRepository.save(transactions);
        teRestClient.useSelectedElement(transactions1.getId());

        return transactions1;
    }


//******************************* END OF CORIS BANK SERVICE *********************************************************************************************




    //******************************* TMONEY *******************************************************************************************************//

    public String getTmoneyApiKey(){

        RestTemplate restTemplate = new RestTemplate();
        // Login et recuperation du token

        JSONObject obj = new JSONObject();
        String token = null;
        obj.put("nomUtilisateur", TransactionInService.Credentials.nomUtilisateur);
        obj.put("motDePasse", TransactionInService.Credentials.motDePasse);
        String urlLogin="https://ms-tpc-prep.togocom.tg/login";

        input.LoginCinetPayRequest result = restTemplateBuilder.build()
                .postForObject(urlLogin, obj, LoginCinetPayRequest.class);
        token = result.getData().getToken();
        return token;
    }

    @Override
    public TmoneyDebitResponse tmoneyDebit(String phone, Double amount) {

        String token = this.getTmoneyApiKey();

        RestTemplate restTemplate = new RestTemplate();

        System.out.println("======================Token Tmoney============================");
        System.out.println(token);
        System.out.println("==================================================");

        String url="https://ms-push-api-prep.togocom.tg/tmoney-middleware/debit";
        Map<String, Object> obj = new HashMap<>();
        //JSONObject obj = new JSONObject();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateHeureRequete = dtf.format(LocalDateTime.now());
      //  Date d = new Date();
       // String timpes =  (1999+d.getYear())+"-"+d.getMonth()+"-"+d.getDate()+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
        Long num = System.currentTimeMillis();
        obj.put("idRequete", "req"+num);
        obj.put("numeroClient","228"+phone.replace(" ", ""));
        obj.put("montant", amount);
        obj.put("refCommande", "ref"+num);
        obj.put("dateHeureRequete",dateHeureRequete);
        obj.put("description", "Encaissement tmoney");

        System.out.println("========================DATA============================");
        System.out.println(obj);
        System.out.println("====================================================");

        // final HttpEntity<String> entity = new HttpEntity<String>(headers);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));


        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(obj, headers);
       // HttpEntity<String> entity = new HttpEntity<String>(obj.toString(), headers);

        System.out.println("========================REQUEST=====================================");
        System.out.println(entity);
        System.out.println("=============================================================");


        //org.springframework.http.HttpEntity<MultiValueMap<String, String>>  request =  new org.springframework.http.HttpEntity<>(map, headers);
//        try {

           // TmoneyDebitResponse value = restTemplate.postForObject(url, entity, TmoneyDebitResponse.class);

            TmoneyDebitResponse value = restTemplateBuilder.build()
                    .postForObject(url, entity, TmoneyDebitResponse.class);
            System.out.println("=============================Response===========================");
            System.out.println(value);
            System.out.println("================================================================");
            return value;
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            return  null;
//        }

    }

    @Override
    public TmoneyDebitResponse getTmoneyApiCheckTransaction(String phone, Double amount){
        String token = this.getTmoneyApiKey();
        //HttpHeaders headers = new HttpHeaders();
        String url="https://ms-push-api-prep.togocom.tg/tmoney-middleware/debit";
        Map<String, Object> obj = new HashMap<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateHeureRequete = dtf.format(LocalDateTime.now());
//        Date d = new Date();
//        String timpes =  (1999+d.getYear())+"-"+d.getMonth()+"-"+d.getDate()+" "+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
        Long num = System.currentTimeMillis();
        obj.put("idRequete", "req"+num);
        obj.put("numeroClient","228"+phone.replace(" ", ""));
        obj.put("montant", amount);
        obj.put("refCommande", "ref"+num);
        obj.put("dateHeureRequete", dateHeureRequete);
        obj.put("description", "Encaissement tmoney");

        System.out.println(obj);

        // final HttpEntity<String> entity = new HttpEntity<String>(headers);
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("Authorization",  token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(obj, headers);

        //org.springframework.http.HttpEntity<MultiValueMap<String, String>>  request =  new org.springframework.http.HttpEntity<>(map, headers);
        try {
            TmoneyDebitResponse value = restTemplateBuilder.build()
                    .postForObject (url,entity, TmoneyDebitResponse.class);
            System.out.println(value);
            return value;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }

    }



    //******************************* END OF CORIS BANK SERVICE *********************************************************************************************//



}

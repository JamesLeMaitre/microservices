package com.esmc.gestionPayement.Services;

import com.esmc.gestionPayement.Repositories.TransactionAdminRepo;
import com.esmc.gestionPayement.Repositories.TransactionInRepo;
import com.esmc.gestionPayement.Repositories.TransactionInRequestRepo;
import com.esmc.gestionPayement.ServicesInterface.TransactionInRequestServiceInterface;
import com.esmc.gestionPayement.entities.TransactionAdmins;
import com.esmc.gestionPayement.entities.TransactionInRequest;
import com.esmc.gestionPayement.entities.TransactionIns;
import com.esmc.gestionPayement.feign.KsuRestClient;
import com.esmc.gestionPayement.feign.TeRestClient;
import com.esmc.gestionPayement.inputs.CinetPayResponse;
import com.esmc.gestionPayement.inputs.CinetPayTransactionCreateResponse;
import com.esmc.gestionPayement.inputs.TransactionInInput;
import com.esmc.models.Ksu;
import input.LoginCinetPayRequest;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import utiles.Messagerie;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionInRequesterviceRequest implements TransactionInRequestServiceInterface {
    @Autowired
    TeRestClient teRestClient;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    private TransactionInRequestRepo transactionInRequestRepository;

    @Autowired
    private TransactionInRepo transactionInRepository;

    @Autowired
    private TransactionAdminRepo transactionAdminRepo;

    @Autowired
    private KsuRestClient ksuRestClient;

    @Override
    public List<TransactionInRequest> getAll() {
        return transactionInRequestRepository.findAll();
    }

    @Override
    public TransactionInRequest getTransactionInRequestById(Long id) {
        return transactionInRequestRepository.getTransactionInRequestById(id);
    }

    @Override
    public TransactionInRequest getTransactionInRequestByReference(String reference) {
        return  transactionInRequestRepository.getTransactionInRequestByReference(reference);
    }

    @Override
    public TransactionInRequest createTransactionInRequest(TransactionInInput data) {
        String destination = "CinetPay";
        TransactionInRequest transactionInRequest = new TransactionInRequest();
        transactionInRequest.setDestination(destination);
        transactionInRequest.setData(data.toString());
        transactionInRequest.setIdentifiant(data.getLot());
        transactionInRequest.setListOfDetailsmEnchangeLinked( String.join(",",data.getListOfDetailsmEnchangeLinked()));
        transactionInRequest.setMontant(data.getAmount());
        transactionInRequest.setReference(data.getLot());
        return transactionInRequestRepository.save(transactionInRequest);
    }

    @Override
    public TransactionInRequest createTeTransactionInRequest(TransactionInInput data, Long idTe, Long idKsu) {
        String destination = "CinetPay";
        String [] array = data.getListOfDetailsmEnchangeLinked();
        TransactionInRequest transactionInRequest = new TransactionInRequest();
        transactionInRequest.setDestination(destination);
        transactionInRequest.setIdKsu(idKsu);
        transactionInRequest.setIdTe(idTe);
        transactionInRequest.setData(data.toString());
        transactionInRequest.setIdentifiant(data.getLot());
        transactionInRequest.setListOfDetailsmEnchangeLinked( String.join(",",array));
        transactionInRequest.setMontant(data.getAmount());
        transactionInRequest.setReference(data.getLot());
        TransactionInRequest transactionInRequest1= transactionInRequestRepository.save(transactionInRequest);
        for(String id: array){
            Long v = new Long(id);
            System.out.println(v);
                teRestClient.useSelectedElement(v);
        }
        return transactionInRequest1;
    }

    @Override
    public List<TransactionInRequest> getTransactionInRequestByIdTe(Long idTe){
        return transactionInRequestRepository.getTransactionInRequestByIdTe(idTe);
    }
    public String getCinetPayApiKey(){

        RestTemplate restTemplate = new RestTemplate();
        // Login et recuperation du token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("apikey","191905569062bc83f35e1250.41991918");
        map.add("password","EsmcGie2022");

        org.springframework.http.HttpEntity<MultiValueMap<String, String>>  request =  new org.springframework.http.HttpEntity<>(map, headers);

        System.out.println(request.toString());
        String urlLogin="https://client.cinetpay.com/v1/auth/login";


        input.LoginCinetPayRequest result = restTemplateBuilder.build()
                .postForObject(urlLogin, request, LoginCinetPayRequest.class);
        String token = result.getData().getToken();
        return token;

    }


    public Object createContact(String prefix, String contact, String nom, String prenom, String email) {


        JSONObject obj = new JSONObject();

        String token = this.getCinetPayApiKey();
        String url = "https://client.cinetpay.com/v1/transfer/contact?token="+token;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        obj.put("prefix", prefix);
        obj.put("phone", contact);
        obj.put("name", prenom);
        obj.put("surname", nom);
        obj.put("name", prenom);
        obj.put("email", email);

       // JSONObject[] objTab = {obj};

        JSONArray ja = new JSONArray();
        ja.appendElement(obj);
        System.out.println("Request for Decaissement = "+ja);

        map.add("data", ja.toString());

        HttpEntity<MultiValueMap<String, String>>  request =  new HttpEntity<>(map, headers);

        System.out.println(request);


        return restTemplateBuilder.build().postForObject(url, request, Object.class);

    }

    @Override
    public Object createDecaissement(double amount, String phone, String prefix, String nom, String prenom, String email, TransactionAdmins transactionAdmins) {

        this.createContact(prefix, phone, nom, prenom, email);

        Ksu k = ksuRestClient.getKsuById(transactionAdmins.getIdKsu());

        Messagerie m = new Messagerie();

        String numero = prefix+phone.replace(" ", "");

        String token = this.getCinetPayApiKey();
        String transaction_id = System.currentTimeMillis()+"";
        String url="https://client.cinetpay.com/v1/transfer/money/send/contact?token="+token+"&transaction_id="+transaction_id;

        JSONObject obj = new JSONObject();
        //for dev
        String notify_url = "https://api.esmcgie.com/PAYEMENT-SERVICE/api/in/transaction/request/cinetpay/webhook/notification";
       // String notify_url = "https://api-dev.esmcgie.com/PAYEMENT-SERVICE/api/in/transaction/request/cinetpay/webhook/notification";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        obj.put("prefix", prefix);
        obj.put("phone", phone.replace(" ", ""));
        obj.put("amount", amount);
        //obj.put("client_transaction_id", client_transaction_id);
        obj.put("notify_url", notify_url);

        JSONArray ja = new JSONArray();
        ja.appendElement(obj);
        System.out.println("Request for Decaissement = "+ja);

        map.add("data", ja.toString());

        org.springframework.http.HttpEntity<MultiValueMap<String, String>>  request =  new org.springframework.http.HttpEntity<>(map, headers);

        System.out.println("Request for new Decaissement : "+request);

        System.out.println(k.getEmail());


        CinetPayTransactionCreateResponse value = restTemplateBuilder.build().postForObject(url, request, CinetPayTransactionCreateResponse.class);
        transactionAdmins.setStatus(1);
        //transactionAdmins.setTransactionId(transaction_id);
        transactionAdmins.setSource("CinetPay");
        transactionAdmins.setReference(value.getData()[0][0].getLot());
        transactionAdminRepo.save(transactionAdmins);
        m.sendSms(numero, "Votre demande est en cours de traitrement, Veuillez attendre 24 h au maximum pour recevoir votre BPSD EV");
       // m.sendMail(k.getEmail(), "Décaissement de MPRg", "Votre demande est en cours de traitrement, Veuillez attendre 24h au maximum pour recevoir votre MEV");
        return value;
    }


    public CinetPayResponse getCinetPayApiCheckTransaction(String token, String transaction_id){
        String url="https://client.cinetpay.com/v1/transfer/check/money?token="+token+"&lot="+transaction_id;
        try {
            return restTemplateBuilder.build()
                    .getForObject (url, CinetPayResponse.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }

    }

    public Object  treatTransaction(TransactionInRequest tr){
        System.out.println("Transaction info");
        Boolean confirmed = null;
        /*
        null - transaction is pending and have not last 24 hours
        true - transaction is confirmed and have to be move in to transaction as confirmed transaction and it related MPRG will be set as alerady used
        false - transaction is not  confirmed and have to be move in to transaction as not  confirmed transaction and it related MPRG will be set to free and can be used
        * **/
        if (tr == null) return false;
        String token = this.getCinetPayApiKey();
        CinetPayResponse element = this.getCinetPayApiCheckTransaction(token,tr.getReference());
        String status="";
        if (element == null){
            confirmed = false;
        }else{
            status = element.getData()[0].getSending_status();
            if (Objects.equals(status, "CONFIRM")){
                confirmed = true;
            } else if (Objects.equals(status, "PENDING")){
                Date currentDate = new Date();
                currentDate.setDate(currentDate.getDate()-1);
                if (tr.getDateCreate().compareTo(currentDate)<0){
                    confirmed = false;
                }else{
                    confirmed = null;
                }

            }
        }
        /*System.out.println("Transaction Status:"+confirmed);
        System.out.println("Current Transaction:"+tr);
        if(element != null){
            System.out.println("Response Status:"+status);
            System.out.println("Transaction Check Response:"+element.toString());
        }
        // return confirmed;
        // return element;
        //treat element to see if it validated or not
        //*/
        if (confirmed != null) {
            TransactionIns transactionIns = new TransactionIns();
            transactionIns.setDestination(tr.getDestination());
            transactionIns.setIdKsu(tr.getIdKsu());
            transactionIns.setIdTe(tr.getIdTe());
            transactionIns.setData(tr.getData());
            transactionIns.setMontant(tr.getMontant());
            transactionIns.setReference(tr.getReference());
            transactionIns.setConfirmed(confirmed);
            transactionIns.setIdentifiant(tr.getIdentifiant());
            transactionIns.setListOfDetailsmEnchangeLinked(tr.getListOfDetailsmEnchangeLinked());

            String [] array = (tr.getListOfDetailsmEnchangeLinked()+"").split(",");
            for(String id: array){
                Long v = new Long(id);
                System.out.println(v);
                int stat=0;
                if(confirmed){
                    stat=1;
                }
                teRestClient.useChangeSelectedElement(v,stat);
            }

            transactionInRequestRepository.delete(tr);
            //return transactionInRepository.save(transactionIns);
            return transactionInRepository.save(transactionIns);
        }else{
            return null;
        }

    }


    public Object  treatTransactionAdmin(TransactionAdmins tr){
        System.out.println("Transaction info");
        Boolean confirmed = null;

        /*
        null - transaction is pending and have not last 24 hours
        true - transaction is confirmed and have to be move in to transaction as confirmed transaction and it related MPRG will be set as alerady used
        false - transaction is not  confirmed and have to be move in to transaction as not  confirmed transaction and it related MPRG will be set to free and can be used
        * **/
        if (tr == null) return false;
        String token = this.getCinetPayApiKey();
        CinetPayResponse element = this.getCinetPayApiCheckTransaction(token,tr.getReference());
        String status="";
        if (element == null){
            confirmed = false;
        }else{
            status = element.getData()[0].getSending_status();
            if (Objects.equals(status, "CONFIRM")){
                confirmed = true;
                tr.setStatus(2);
            } else if (Objects.equals(status, "PENDING")){
                Date currentDate = new Date();
                currentDate.setDate(currentDate.getDate()-1);
                if (tr.getDateCreate().compareTo(currentDate)<0){
                    confirmed = false;
                    tr.setStatus(0);
                }else{
                    confirmed = null;
                }

            }
        }
        transactionAdminRepo.save(tr);
        /*System.out.println("Transaction Status:"+confirmed);
        System.out.println("Current Transaction:"+tr);
        if(element != null){
            System.out.println("Response Status:"+status);
            System.out.println("Transaction Check Response:"+element.toString());
        }
        // return confirmed;
        // return element;
        //treat element to see if it validated or not
        //*/
        if (confirmed != null) {
            TransactionIns transactionIns = new TransactionIns();
            transactionIns.setDestination("CinetPay");
            transactionIns.setIdKsu(tr.getIdKsu());
            transactionIns.setIdTe(tr.getIdTe());
            transactionIns.setData("");
            transactionIns.setMontant(tr.getMontant());
            transactionIns.setReference(tr.getReference());
            transactionIns.setConfirmed(confirmed);
            transactionIns.setIdentifiant(tr.getReference());
          //  transactionIns.setListOfDetailsmEnchangeLinked(tr.getListOfDetailsmEnchangeLinked());

//            String [] array = (tr.getListOfDetailsmEnchangeLinked()+"").split(",");
//            for(String id: array){
//                Long v = new Long(id);
//                System.out.println(v);
//                int stat=0;
//                if(confirmed){
//                    stat=1;
//                }
//                teRestClient.useChangeSelectedElement(v,stat);
//            }

          //  transactionInRequestRepository.delete(tr);
            //return transactionInRepository.save(transactionIns);
            return transactionInRepository.save(transactionIns);
        }else{
            return null;
        }

    }

    @Override
    public Object  checkTransaction(){
        //System.out.println("Transaction info");
        List<TransactionInRequest> trList = transactionInRequestRepository.getLast(PageRequest.of(0,50));

        for(TransactionInRequest tr : trList){
            this.treatTransaction(tr);
        }
        return "Done";

    }

    @Override
    public Object  checkTransactionAdmin(){
        //System.out.println("Transaction info");
        List<TransactionAdmins> trList = transactionAdminRepo.getLast(PageRequest.of(0,50));

        for(TransactionAdmins tr : trList){
            this.treatTransactionAdmin(tr);
        }
        return "Done";

    }

    @Override
    public void cinetPayWebhooksVerifyTransaction(String transactionId) {
        TransactionAdmins transactionAdmins = transactionAdminRepo.getTransactiontByIdTransactionCinetPayId(transactionId);
        System.out.println(transactionAdmins);
        this.treatTransactionAdmin(transactionAdmins);
    }

    @Override
    public void cinetPayWebhooksVerifyTransactionDepot(String transactionId) {
        TransactionAdmins transactionAdmins = transactionAdminRepo.getTransactiontByIdTransactionId(transactionId);
        System.out.println(transactionAdmins);
        this.treatTransactionAdmin(transactionAdmins);
    }
}

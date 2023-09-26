package com.esmc.gestionPayement.Controllers;

import com.esmc.gestionPayement.Repositories.TransactionRepo;
import com.esmc.gestionPayement.ServicesInterface.TransactionServiceInterface;
import com.esmc.gestionPayement.entities.Transactions;
import com.esmc.gestionPayement.feign.CinetPayClient;
import com.esmc.gestionPayement.feign.MaBanKsuRestClient;
import com.esmc.gestionPayement.feign.TeRestClient;
import com.esmc.gestionPayement.inputs.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import utiles.DataFormatter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
@RestController
@RequestMapping("api/out/transaction")
public class TransactionsController extends DataFormatter<Transactions> {



    @Autowired
    private TransactionServiceInterface transactionService;
    @Autowired
    private MaBanKsuRestClient maBanKsuRestClient;

    @Autowired
    private TeRestClient teRestClient;

    @Autowired
    private CinetPayClient cinetPayClient;
    @Autowired
    private TransactionRepo transactionRepo;

    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    HttpHeaders headers = new HttpHeaders();


    @GetMapping("/list")
    public Object list () {
        List<Transactions> urtransaction   = transactionService.getAll();
        return renderDataArray(true,urtransaction,"list of transaction");
    }

    @GetMapping("/mabanksu/{mabanksu}/reference/{reference}")
    public Object getTransactionsByReference(@PathVariable("mabanksu") Long mabanksu,@PathVariable("reference") String reference){

        try{
        Transactions usedTransation = transactionService.getTransactionsByReference(reference);
        if(usedTransation==null){
            return  renderStringData(false,"" ,"Cette reference de transaction n'existe pas");
        }
        if(!usedTransation.getStatus()){
            return  renderStringData(false,"" ,"Cette reference de transaction est deja utilise");
        }
        MaBanKsuInput maBanKsuInput = new MaBanKsuInput();
        maBanKsuInput.setMabanksu(mabanksu);
        maBanKsuInput.setTotal(usedTransation.getMontant().intValue());
        maBanKsuRestClient.saveMbanKsu(maBanKsuInput);
        usedTransation.setStatus(false);
        transactionRepo.save(usedTransation);
        return  renderData(true,transactionService.getTransactionsByReference(reference),"Transaction by refernce");
    } catch (Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        return  renderStringData(false,"Error while processing" ,exceptionAsString);
    }

    }

    @GetMapping("/check/reference/{reference}")
    public Object checkTransactionsByReference(@PathVariable("reference") String reference){
        try{
        Transactions usedTransation = transactionService.getTransactionsByReference(reference);
        if(usedTransation==null){
            return  renderStringData(false,"Error while procssing" ,"Cette reference de transaction n'existe pas");
        }
        if(usedTransation.getStatus()==false){
            return  renderStringData(false,"Error while procssing" ,"Cette reference de transaction est deja utilise");
        }
       // usedTransation.setStatus(false);
       // transactionRepo.save(usedTransation);
        return  renderData(true,transactionService.getTransactionsByReference(reference),"Transaction by refernce");
    } catch (Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        return  renderStringData(false,"Error while procssing" ,exceptionAsString);
    }

    }

    @GetMapping("/te/reference/{reference}")
    public Object getTeTransactionsByReference(@PathVariable("reference") String reference){
       try{

        Transactions usedTransation = transactionService.getTransactionsByReference(reference);

        if(usedTransation==null){
            return  renderStringData(false,"" ,"Cette reference de transaction n'existe pas");
        }
//        if(usedTransation.getStatus()==false){
//            return  renderStringData(false,"" ,"Cette reference de transaction n'est pas active");
//        }
        if(usedTransation.getUsed()==true){
            return  renderStringData(false,"       " ,"Cette reference de transaction est deja utilise");
        }

       // teRestClient.approvisionnementBAn(usedTransation.getMontant(), usedTransation.getIdTe());
            teRestClient.gennerateMev(usedTransation.getId());
        usedTransation.setStatus(false);
        usedTransation.setUsed(true);
        transactionRepo.save(usedTransation);
        return  renderData(true,transactionService.getTransactionsByReference(reference),"Transaction by refernce");
       } catch (Exception e) {
           StringWriter sw = new StringWriter();
           e.printStackTrace(new PrintWriter(sw));
           String exceptionAsString = sw.toString();
           return  renderStringData(false,"Error while procssing" ,exceptionAsString);
       }

    }
    @GetMapping("/{id}")
      public Transactions getTransactionsById(@PathVariable("id") Long id){
        return transactionService.getTransactionsById(id);
    }

    @PostMapping("/extern/data")
    public Object createTransactionsAll(@RequestBody() TransactionInput data){
        if(transactionService.getTransactionsByReference(data.getData().getOperator_id())==null){
            return  renderData(true,transactionService.createTransaction(data),"Create transaction");
        }
        return  renderStringData(false,"Error while procssing" ,"Refernce already used");
    }

    @PostMapping("/extern/data/cinetpay")
    public Object createTransactions(@RequestBody() TransactionInput data){
        if(transactionService.getTransactionsByReference(data.getData().getOperator_id())==null){
            return  renderData(true,transactionService.createTransaction(data),"Create transaction");
        }
        return  renderStringData(false,"Error while procssing" ,"Refernce already used");
    }

    @PostMapping("/extern/data/tmoney")
    public Object createTmoneyTransactions(@RequestBody() TransactionInput data){
        if(transactionService.getTransactionsByReference(data.getData().getOperator_id())==null){
            return  renderData(true,transactionService.createTransaction(data),"Create transaction");
        }
        return  renderStringData(false,"Error while procssing" ,"Refernce already used");
    }

    @GetMapping("/by/te/{idTe}")
    public Object  getTransactionsByTe( @PathVariable("idTe") Long idTe){
        List<Transactions> urtetrans = transactionService.getTransactionByIdTe(idTe,false);
        return  renderDataArray(true,urtetrans ,"list of transaction by idte not used");
    }

    @GetMapping("used/by/te/{idTe}")
    public Object getTransactionsUsedByTe( @PathVariable("idTe") Long idTe){
        List<Transactions> urtran = transactionService.getTransactionByIdTe(idTe,true);
        return  renderDataArray(true,urtran,"list of transaction by idte  used");
    }

    @PostMapping("/extern/te/{idTe}/ksu/{idKsu}/data/")
    public Object createTeTransactions(@RequestBody() TransactionInput data, @PathVariable("idTe") Long idTe, @PathVariable("idKsu") Long idKsu){

        if(transactionService.getTransactionsByReference(data.getData().getOperator_id())==null){

            Transactions transactions = transactionService.createTeTransaction(data,idTe,idKsu);
            return  renderData(true,transactions,"Create te transaction");
        }
        return  renderStringData(false,"Error while procssing" ,"Reference already used");
    }

    @GetMapping("/extern/data/{id}")
    public Object checkTransaction(@PathVariable("id") Long id){
        return  renderStringData(true,"","Ping transaction data ");
    }

    @PostMapping("/decaissement/data")
    public Object decaissementTransaction(@RequestBody DecaissementInput decaissementInput) throws IOException {


        RestTemplate restTemplate = new RestTemplate();
        // Login et recuperation du token
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("apikey","191905569062bc83f35e1250.41991918");
        map.add("password","EsmcGie2022");

        org.springframework.http.HttpEntity<MultiValueMap<String, String>>  request =  new org.springframework.http.HttpEntity<>(map, headers);

        System.out.println(request.toString());
        String urlLogin="https://client.cinetpay.com/v1/auth/login";


        JSONObject obj = new JSONObject();
        obj.put("apikey","191905569062bc83f35e1250.41991918");
        obj.put("password","EsmcGie2022");
        LoginCinetPayRequest result = restTemplateBuilder.build()
                .postForObject(urlLogin, request, LoginCinetPayRequest.class);
        String token = result.getData().getToken();
        return token;



        /*JsonNode root = objectMapper.readTree(result);
        System.out.println(root);
        String token = root.get("data").get("token").toString();*/

        // Creation du contact

    /*MultiValueMap<String, String> mapContact = new LinkedMultiValueMap<>();
    map.add("token",token);
    map.add("lang","fr");

    String urlContact="https://client.cinetpay.com/v1/auth/login";
    String resultContact = restTemplate.postForObject(urlLogin, request, String.class);
    JsonNode rootContact = objectMapper.readTree(result);*/

        //return renderStringData(true,"","");
    }

    @GetMapping("/by/ksu/{idKsu}/te/{idTe}")
    public Object getTransactionsByKsuAndTe(@PathVariable("idKsu") Long idKsu, @PathVariable("idTe") Long idTe){
        Transactions t = transactionService.getTransactionByIdKsuAndIdTe(idKsu, idTe);
        return  renderData(true,t,"Current transaction");
    }

    // Fonction pour l'approvisionnement en MABAn

    @GetMapping("/approvisionnentBAn/montant/{montant}/ksu/{idKsu}/te/{idTe}")
    public Object createTransAppro(@PathVariable("montant") Double montant, @PathVariable("idKsu") Long idKsu, @PathVariable("idTe") Long idTe){

        Double montantTotal = transactionService.sommeMev(idKsu, idTe);

        if (montant <= montantTotal) {

            teRestClient.mutationProcessusMevBan(idTe, montant);

            return  renderData(true,transactionService.createTansApro(montant, idKsu, idTe),"Opperation succeed");
        } else {
            return renderStringData(false, "Oppération échoué", "Opperation failed");
        }
    }


    // Fonction pour calculer la somme des Transactions d'encaissement d'un Ksu

    @GetMapping("/somme/ksu/{idKsu}/te/{idTe}")
    public Double sommeTransactionsByKsuAndTe(@PathVariable("idKsu") Long idKsu, @PathVariable("idTe") Long idTe){
        return  transactionService.sommeMev(idKsu, idTe);
    }


    // ***************************************************CORIS BANK************************************************************************************ //

    //________________ le client paie a l'entreprise -----------------------------------------
    @PostMapping("/sendOTPCode")
    public Object sendYourOTPCode(@RequestBody DemandeOtpInput data) throws Exception {
        String response = (String) transactionService.sendOTPCode(data);
        return  renderStringData(true, response, "Opération éffectuer avec succès");
    }
    //ENCAISSEMENT POUR ESMC ET DECAISSEMENT POUR LE CLIENT
    @PostMapping("/payGoodWithReceivedCode")
    public Object proceedToPayment(@RequestBody PayementBienInput data) throws Exception {
        String response = (String) transactionService.makePaymentForGoods(data);
        return  renderStringData(true, response, "Opération éffectuer avec succès");
    }

    //________________ L'entreprise paie au client-----------------------------
    // ------------
    @PostMapping("/payOnInternet")
    public Object proceedToInternetPayment(@RequestBody PayementInternetInput data) throws Exception {
        String response = transactionService.makeInternetPayement(data);
        return  renderStringData(true, response, "Opération éffectuer avec succès");
    }
    @GetMapping("/checkCorisTransaction/{codeOperation}")
    public Object checkTransactionOfCoris(@PathVariable String codeOperation ) throws Exception{
        String response = (String) transactionService.checkTransactionStatus(codeOperation);
        return  renderStringData(true, response, " Opération éffectuer avec succès : la transaction a été bien vérifié");
    }

    @GetMapping("/clients/{countryCode}/{phone}")
    public Object getClientInformation(@PathVariable String countryCode, @PathVariable String phone) throws Exception {
//        ClientInfosInput cl = new ClientInfosInput();
//        HashMap<String, String> map = new HashMap<>();
//        map.put("countryCode", countryCode);
//        map.put("phone", phone);

        String response = (String) transactionService.recoverClientInformation(countryCode,phone);
        return renderStringData(true, response, "Opération éffectuer avec succès");
//        return getClientInfo(requestOptions.getClientId(), requestOptions.getClientSecret(), map);
    }

//
//    @PostMapping("/payement_internet")
//    public Object paymentInternet(@RequestBody PayementInternetInput data) throws Exception {
//        return  transactionService.internetPayement(data);
//    }
//


    //******************************************** END OF CORIS BANK ************************************************************************************** //

    @GetMapping("/tmoney_debit/phone/{phone}/montant/{montant}")
    public Object tmoneyDebit(@PathVariable("phone") String phone, @PathVariable("montant") Double montant) {

        DataFormatter<TmoneyDebitResponse> data = new DataFormatter<>();

        TmoneyDebitResponse item = transactionService.getTmoneyApiCheckTransaction(phone, montant);

        if (item == null) {
            return data.renderStringData(false, "", "Opération échoué");
        }
        return data.renderData(true, item, "Opération éfectuer avec succès");
    }

}

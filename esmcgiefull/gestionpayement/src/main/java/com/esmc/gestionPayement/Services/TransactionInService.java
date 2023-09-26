package com.esmc.gestionPayement.Services;

import com.esmc.gestionPayement.Repositories.TransactionInRepo;
import com.esmc.gestionPayement.feign.TeRestClient;
import com.esmc.gestionPayement.ServicesInterface.TransactionInServiceInterface;
import com.esmc.gestionPayement.entities.TransactionIns;
import com.esmc.gestionPayement.inputs.CorisInput;
import com.esmc.gestionPayement.inputs.TmoneyInput;
import com.esmc.gestionPayement.inputs.TmoneyReponse;
import com.esmc.gestionPayement.inputs.TransactionInInput;
import input.LoginCinetPayRequest;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TransactionInService implements TransactionInServiceInterface {
    @Autowired
    private TransactionInRepo transactionInRepository;

    @Autowired
    TeRestClient teRestClient;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;



    @Override
    public List<TransactionIns> getAll() {
        return transactionInRepository.findAll();
    }

    @Override
    public TransactionIns getTransactionInsById(Long id) {
        return transactionInRepository.getTransactionInsById(id);
    }

    @Override
    public TransactionIns getTransactionInsByReference(String reference) {
        return  transactionInRepository.getTransactionInsByReference(reference);
    }

    @Override
    public TransactionIns createTransactionIn(TransactionInInput data) {
        String destination = "CinetPay";
        TransactionIns transactionIns = new TransactionIns();
        transactionIns.setDestination(destination);
        transactionIns.setData(data.toString());
        transactionIns.setMontant(data.getAmount());
        transactionIns.setReference(data.getLot());
        return transactionInRepository.save(transactionIns);
    }

    @Override
    public TransactionIns createTeTransactionIn(TransactionInInput data, Long idTe, Long idKsu) {
        String destination = "CinetPay";
        TransactionIns transactionIns = new TransactionIns();
        transactionIns.setDestination(destination);
        transactionIns.setIdKsu(idKsu);
        transactionIns.setIdTe(idTe);
        transactionIns.setData(data.toString());
        transactionIns.setMontant(data.getAmount());
        transactionIns.setReference(data.getLot());
        return transactionInRepository.save(transactionIns);
    }

    public class Credentials {
        public static final String nomUtilisateur = "ecms";
        public static final String motDePasse = "mD@9FMr@c80zQqTYyiow";
    }


    public String getTmoneyApiKey(){

        RestTemplate restTemplate = new RestTemplate();
        // Login et recuperation du token

        JSONObject obj = new JSONObject();
        String token = null;
        obj.put("nomUtilisateur",Credentials.nomUtilisateur);
        obj.put("motDePasse",Credentials.motDePasse);
        String urlLogin="https://ms-tpc-prep.togocom.tg/login";


        input.LoginCinetPayRequest result = restTemplateBuilder.build()
                .postForObject(urlLogin, obj, LoginCinetPayRequest.class);
        token = result.getData().getToken();
        return token;

    }

    public TmoneyReponse getTmoneyApiCheckTransaction(String phone, Double amount){
        String token = this.getTmoneyApiKey();
        //HttpHeaders headers = new HttpHeaders();
        String url="https://ms-tpc-prep.togocom.tg/api/tpcredit";
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
        obj.put("description", "deciassement tmoney");

        System.out.println(obj);

       // final HttpEntity<String> entity = new HttpEntity<String>(headers);
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.add("Authorization",  token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

       HttpEntity<Map<String, Object>> entity = new HttpEntity<>(obj, headers);

        //org.springframework.http.HttpEntity<MultiValueMap<String, String>>  request =  new org.springframework.http.HttpEntity<>(map, headers);
        try {
            TmoneyReponse value = restTemplateBuilder.build()
                    .postForObject (url,entity, TmoneyReponse.class);
            System.out.println(value);
            return value;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }

    }

    @Override
    public Object decaissement(TmoneyInput data, Long idTe, Long idKsu) {

        TmoneyReponse element = this.getTmoneyApiCheckTransaction(data.getPhone(),data.getAmount());

        return element;

    }

    @Override
    public List<TransactionIns> getTransactionInByIdTe(Long idTe){
        return transactionInRepository.getTransactionInByIdTe(idTe);
    }





}

package com.esmc.mobileMoneyCredit.controller;

import com.esmc.models.MessageSms;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/sms")
public class SmsApiController {

    @PostMapping("/send")
    public String sendSms(@RequestBody MessageSms request){

        System.out.println("Les donnée : "+request.toString());

        RestTemplate restTemplate = new RestTemplate();

//        request.setSenderId("ESMC GIE");
//        request.setApiKey("HjNyLxfmnCkIJqii1ixzZ3kXWFNqtHK6WazCjfVzoy8=");
//        request.setClientId("3c55b877-9bc8-4946-9bb1-fadfaa90c480");
//        request.setMessage("Bonjour M BIKOR");
//        request.setMobileNumbers("22892497695");

        String uri = "http://sms.skegrouptogo.com:6005/api/v2/SendSMS";

        JSONObject object = new JSONObject();
        object.put("SenderId",request.getSenderId());
        object.put("ApiKey",request.getApiKey());
        object.put("ClientId",request.getClientId());
        object.put("Message",request.getMessage());
        object.put("MobileNumbers",request.getMobileNumbers());


        System.out.println("Request : " +request);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, object, String.class);
        System.out.println("la response est "+response);

        return "ok";
    }
}

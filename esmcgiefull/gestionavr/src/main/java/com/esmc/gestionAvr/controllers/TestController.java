package com.esmc.gestionAvr.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.esmc.gestionAvr.response.responseLogin;
import com.esmc.models.DetailsAgr;
import input.LoginCinetPayRequest;
import net.minidev.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;
@RestController
@RequestMapping(value="/api/test")
public class TestController {


    @GetMapping("info")
    private void getList(){
        try {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println(addr);
            String hostname = addr.getHostName();
            System.out.println("Hostname of system = " + hostname);
        } catch (UnknownHostException e) {
            System.out.println(e);
        }
    }

    public class Credentials {
        public static final String nomUtilisateur = "user1";
        public static final String motDePasse = "1234";
    }

     @GetMapping("token")
    private  Object  getToken(){

         InetAddress ip;
         try {
             ip = InetAddress.getLocalHost();
             System.out.println("Your current IP address : " + ip);
             System.out.println("Your current IP address : " + ip.getHostName());
             System.out.println("Your current IP address : " + ip.getHostAddress());
         } catch (UnknownHostException e) {
             e.printStackTrace();
         }

          RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
         // Login et recuperation du token
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
         MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
         map.add("username",Credentials.nomUtilisateur);
         map.add("password",Credentials.motDePasse);

         HttpEntity<MultiValueMap<String, String>>  request =  new HttpEntity<>(map, headers);

         System.out.println(request.toString());
         String urlLogin="http://192.168.22.88:8888/SECURITY-SERVICE/login";


         responseLogin  result = restTemplateBuilder.build()
                 .postForObject(urlLogin, request, responseLogin.class);
         return result;
    }

}

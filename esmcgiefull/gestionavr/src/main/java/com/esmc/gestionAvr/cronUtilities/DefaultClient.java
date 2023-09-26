package com.esmc.gestionAvr.cronUtilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.esmc.gestionAvr.controllers.TestController;
import com.esmc.gestionAvr.response.responseLogin;
import com.esmc.models.TerminalEchange;
import com.esmc.security.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.ParameterizedType;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultClient {

    private  String appAdress = "http://18.221.33.125:8888/";
    private RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();

    private String ipAdress_dev = "http://52.6.235.199:8888/";

    private String ipAdress_test = "https://apis-test.esmcgie.com/";
    private String ipAdress_local = "http://192.168.22.88:8888";

    private String ipAdress_prod = "https://api.esmcgie.com/";



    public String getToken(){
       InetAddress ip;

      //  appAdress = ipAdress_local;

      // appAdress = ipAdress_dev;


        try {
            ip = InetAddress.getLocalHost();
           // appAdress = ipAdress_test;

           // appAdress =  "http://"+ipAdress_dev+":8888/";
            appAdress =  "http://"+ip.getHostAddress()+":8888/";
        } catch (UnknownHostException e) {
           // e.printStackTrace();
        }


        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        // Login et recuperation du token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", TestController.Credentials.nomUtilisateur);
        map.add("password", TestController.Credentials.motDePasse);

        HttpEntity<MultiValueMap<String, String>>  request =  new HttpEntity<>(map, headers);


        String urlLogin=appAdress+"SECURITY-SERVICE/login";


        responseLogin result = restTemplateBuilder.build()
                .postForObject(urlLogin, request, responseLogin.class);
        return "Bearer "+result.getAccessToken();
    }

    public  Object  GetRequest(String urlString, ParameterizedTypeReference retunType){
       try {

        String token = this.getToken();
        Map<String, Object> obj = new HashMap<>();

        String url= appAdress+urlString;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",  token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
        ResponseEntity<?> res = restTemplateBuilder.build()
                .exchange(url,  HttpMethod.GET,entity , retunType);
        return res.getBody();
       } catch (Exception e){
           System.out.println(e.getMessage());
           return null;
       }

    }
}

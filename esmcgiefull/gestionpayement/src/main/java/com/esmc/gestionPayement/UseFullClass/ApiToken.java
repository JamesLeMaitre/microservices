package com.esmc.gestionPayement.UseFullClass;

import input.LoginCinetPayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class ApiToken {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    HttpHeaders headers = new HttpHeaders();

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


        LoginCinetPayRequest result = restTemplateBuilder.build()
                .postForObject(urlLogin, request, LoginCinetPayRequest.class);
        String token = result.getData().getToken();
        return token;

    }
}

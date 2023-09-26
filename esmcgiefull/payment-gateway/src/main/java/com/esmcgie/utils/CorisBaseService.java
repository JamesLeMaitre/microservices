package com.esmcgie.utils;

import com.esmcgie.models.ResponseTransaction;
import com.esmcgie.requests.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.apache.http.impl.client.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import static com.esmcgie.utils.JavaUtils.CORIS_BASE_URL;
import static java.util.Collections.singletonList;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
public abstract class CorisBaseService {

    protected Object getClientInfo(String clientId, String clientSecret, HashMap<String, String> map) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ClientInfo info = mapper.convertValue(map, ClientInfo.class);
        String hashParam = hashRequestParametersByWonder(info.getCountryCode() + "" + info.getPhone() + "" + clientSecret);
        HttpHeaders headers = getHttpHeaders(clientId, hashParam);
        HttpEntity<String> requestBody = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequest());
//        RestTemplate restTemplate = new RestTemplate();
//        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//        requestFactory.setConnectTimeout(1000);
//        requestFactory.setReadTimeout(1000);
//        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<String> response = restTemplate.exchange(CORIS_BASE_URL + "infos-client?codePays=" + info.getCountryCode() + "&telephone=" + info.getPhone(), GET, requestBody, String.class);
        return mapper.convertValue(response.getBody(), Object.class);
    }

    protected ResponseTransaction internetPayment(String clientId, String clientSecret, HashMap<String, String> map) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TransactionInfo info = mapper.convertValue(map, TransactionInfo.class);
        String hashParam = hashRequestParameters(info.getCountryCode() + "" + info.getPhone() + "" + clientSecret);
        HttpHeaders headers = getHttpHeaders(clientId, hashParam);
        HttpEntity<String> requestBody = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequest());
        ResponseEntity<String> response = restTemplate.exchange(CORIS_BASE_URL + "operations/paiementinternet?codePays=" + info.getCountryCode() + "&telephone=" + info.getPhone() + "&codePv=" + info.getCodePv() + "&codeRetrait=" + info.getWithdrawalCode() + "&montant=" + info.getAmount(), POST, requestBody, String.class);
        return mapper.convertValue(response.getBody(), ResponseTransaction.class);
    }

    protected ResponseTransaction paymentOfProperty(String clientId, String clientSecret, HashMap<String, String> map) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        PaymentInfo info = mapper.convertValue(map, PaymentInfo.class);
        String hashParam = hashRequestParameters(info.getCountryCode() + "" + info.getPhone() + "" + "" + info.getCodePv() + "" + info.getAmount() + "" + info.getCodeOTP() + "" + clientSecret);
        HttpHeaders headers = getHttpHeaders(clientId, hashParam);
        HttpEntity<String> requestBody = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequest());
        ResponseEntity<String> response = restTemplate.exchange(CORIS_BASE_URL + "operations/paiement-bien?codePays=" + info.getCountryCode() + "&telephone=" + info.getPhone() + "&codePv=" + info.getCodePv() + "&montant=" + info.getAmount() + "&codeOTP=" + info.getCodeOTP(), POST, requestBody, String.class);
        return mapper.convertValue(response.getBody(), ResponseTransaction.class);
    }

    protected void setOTPCode(String clientId, String clientSecret, HashMap<String, String> map) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        OtpInfo info = mapper.convertValue(map, OtpInfo.class);
        String hashParam = hashRequestParameters(info.getCountryCode() + "" + info.getPhone() + "" + clientSecret);
        HttpHeaders headers = getHttpHeaders(clientId, hashParam);
        HttpEntity<String> requestBody = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequest());
        ResponseEntity<String> response = restTemplate.exchange(CORIS_BASE_URL + "send-code-otp?codePays=" + info.getCountryCode() + "&telephone=" + info.getPhone(), POST, requestBody, String.class);
        log.info("{}", response.getBody());
    }

    protected  void  setTransactionStatus(String clientId, String clientSecret, HashMap<String, String> map) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CheckTransactionStatus transStatus = mapper.convertValue(map, CheckTransactionStatus.class);
        String hashParam = hashRequestParameters(transStatus.getCodeOperation() + ""  + clientSecret);
        HttpHeaders headers = getHttpHeaders(clientId, hashParam);
        HttpEntity<String> requestBody = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequest());
        ResponseEntity<String> response = restTemplate.exchange(CORIS_BASE_URL + "transaction-status?codeOperation=" + transStatus.getCodeOperation() , POST, requestBody, String.class);


    }

    protected String hashRequestParameters(String parameter) throws Exception {
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequest());
        HttpHeaders headers = getHttpHeaders();
        HttpEntity<String> requestBody = new HttpEntity<>(headers);


        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000); // 5 seconds
        requestFactory.setReadTimeout(10000); // 10 seconds

//        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//        connection.setConnectTimeout(5000);
//        connection.setReadTimeout(10000);

        ResponseEntity<String> response = restTemplate.exchange(CORIS_BASE_URL + "hash256?originalString=" + parameter, GET, requestBody, String.class);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(response.getBody(), String.class);
    }

       // by wonder
//    protected String hashRequestParametersByWonder(String originalString) throws Exception{
//        String sha256hex = Hashing.sha256().hashString(originalString, StandardCharsets.UTF_8).toString();
//       return sha256hex;
//    }
       protected String hashRequestParametersByWonder(String originalString) throws Exception {
           String sha256hex = Hashing.sha256().hashString(originalString, StandardCharsets.UTF_8).toString();
           return sha256hex;
       }


    private HttpHeaders getHttpHeaders(String clientId, String hashParam) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.set("hashParam", hashParam);
        headers.set("clientId", clientId);
        return headers;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.setAccept(singletonList(APPLICATION_JSON));
        return headers;
    }

//    public HttpComponentsClientHttpRequestFactory getClientHttpRequest() throws Exception {
//        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
//        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create().setSSLSocketFactory(csf).build();
//        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connectionManager).build();
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setHttpClient(client);
//        return requestFactory;
//    }

    public HttpComponentsClientHttpRequestFactory getClientHttpRequest() throws Exception {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create().setSSLSocketFactory(csf).build();
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connectionManager).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(client);
        return requestFactory;
    }

//    without setHttpClient()
//    public HttpComponentsClientHttpRequestFactory getClientHttpRequest() throws Exception {
//        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
//        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
//        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext);
//        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create().setSSLSocketFactory(connectionFactory).build();
//        return new HttpComponentsClientHttpRequestFactory(HttpClients.custom().setConnectionManager(connectionManager).build());
//    }

//    jdk 8
//    public HttpComponentsClientHttpRequestFactory getClientHttpRequest() throws Exception {
//        TrustManager[] trustAllCerts = new TrustManager[]{
//                new X509TrustManager() {
//                    @Override
//                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                        return null;
//                    }
//
//                    @Override
//                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                    }
//
//                    @Override
//                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                    }
//                }
//        };
//        SSLContext sslContext = SSLContext.getInstance("SSL");
//        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//        PoolingHttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create().setSSLSocketFactory(csf).build();
//        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connectionManager).build();
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setHttpClient(client);
//        return requestFactory;
//    }




}

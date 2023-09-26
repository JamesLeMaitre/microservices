package utiles;

import com.esmc.models.MessageSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class Messagerie {

    @Autowired
    private  JavaMailSender emailSender;

    public  Object sendSms(String numero,String message){
        RestTemplate restTemplate = new RestTemplate();

        MessageSms request = new MessageSms();
        request.setSenderId("ESMC GIE");
        request.setApiKey("HjNyLxfmnCkIJqii1ixzZ3kXWFNqtHK6WazCjfVzoy8=");
        request.setClientId("3c55b877-9bc8-4946-9bb1-fadfaa90c480");
        request.setMessage(message);
        request.setMobileNumbers(numero.replace("+",""));

        // smsApiRestClient.sendSms(request);

        String uri = "http://sms.skegrouptogo.com:6005/api/v2/SendSMS";

        Map<String, Object> object = new HashMap<>();
        object.put("SenderId",request.getSenderId());
        object.put("ApiKey",request.getApiKey());
        object.put("ClientId",request.getClientId());
        object.put("Message",request.getMessage());
        object.put("MobileNumbers",request.getMobileNumbers());


        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(object, headers);


        System.out.println("Request : " +object);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, entity, String.class);

        return response;

    }

    public  void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("esmcgie@esmcgie.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

}

package com.esmc.demandeAchatBanKsu.feign;



import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.MessageSms;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MMCREDIT-SERVICE", configuration = FeignClientConfiguration.class)
public interface SmsApiRestClient {

    @PostMapping("api/sms/send")
    public String sendSms(@RequestBody MessageSms request);
}

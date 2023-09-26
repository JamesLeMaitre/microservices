package com.esmc.gestionPayement.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.gestionPayement.inputs.CinetPayLoginInput;
import com.esmc.gestionPayement.inputs.MaBanKsuInput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "client.cinetpay.com", configuration = FeignClientConfiguration.class)
public interface CinetPayClient {
    @PostMapping(value = "/api/v1/auth/login", headers = "Accept=application/X-WWW-form-urlencoded")
    public Object login(@RequestBody CinetPayLoginInput loginInfo);
}

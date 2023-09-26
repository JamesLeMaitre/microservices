package com.esmc.gestionformation.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.Extrant;
import com.esmc.models.ExtrantInputv2;
import com.esmc.models.Formatter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "AVR-SERVICE", configuration = FeignClientConfiguration.class)
public interface AvrRestClient {

    @PostMapping("add/supportv2")
    Formatter<Extrant> create(@RequestBody() ExtrantInputv2 data);
}

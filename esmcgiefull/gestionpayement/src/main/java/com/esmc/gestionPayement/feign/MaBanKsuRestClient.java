package com.esmc.gestionPayement.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.gestionPayement.inputs.MaBanKsuInput;
import com.esmc.models.BanKsu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ACHATKSU-SERVICE", configuration = FeignClientConfiguration.class)
public interface MaBanKsuRestClient {

    @PostMapping("/api/BanKsu/save")
    public BanKsu saveMbanKsu(@RequestBody MaBanKsuInput data);
}

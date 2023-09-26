package com.esmc.gestionAvr.feign;

import com.esmc.feign.FeignClientCronConfiguration;
import com.esmc.models.BanKsu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ACHATKSU-SERVICE", configuration = FeignClientCronConfiguration.class)
public interface AchatKsuRestClient {

    @GetMapping("/api/BanKsu/code_ban/{codeBanKsu}")
    public BanKsu getBanKsuByCodeBan(@PathVariable("codeBanKsu") String codeBanKsu);

}

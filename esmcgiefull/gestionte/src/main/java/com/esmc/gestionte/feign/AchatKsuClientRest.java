package com.esmc.gestionte.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.BanKsu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ACHATKSU-SERVICE", configuration = FeignClientConfiguration.class)
public interface AchatKsuClientRest {

    @GetMapping("api/BanKsu/code_ban/{codeBanKsu}")
    public BanKsu getBanKsuByCodeBan(@PathVariable("codeBanKsu") String codeBanKsu);

}

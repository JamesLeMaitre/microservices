package com.esmc.gestionKsu.feign;



import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.BanKsu;
import com.esmc.models.MaBanKsu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ACHATKSU-SERVICE", configuration = FeignClientConfiguration.class)
public interface MaBanKsuRestClient {

    @GetMapping("/api/MaBanKsu/typeMaBanKsu/{id}")
     MaBanKsu getMaBanKsu(@PathVariable Long id);

    @GetMapping("/api/MaBanKsu/get/{id}")
     MaBanKsu mabanKsu(@PathVariable("id") Long id);

    @GetMapping("/api/MaBanKsu/curentMaBanKsuId")
     MaBanKsu getCurrentMaBanKsuById();

    @GetMapping("/api/BanKsu/code_ban/{codeBanKsu}")
    public BanKsu getBanKsuByCodeBan(@PathVariable("codeBanKsu") String codeBanKsu);
}

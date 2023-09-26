package com.esmc.demandeAchatBanKsu.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.input.KsuCheckInput;
import com.esmc.models.Ksu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "KSU-SERVICE", configuration = FeignClientConfiguration.class)
public interface KsuRestClient {

    @GetMapping("api/ksus/current")
    public Ksu getCurrentKsuById();

    @GetMapping("api/ksus/getById/{id}")
    public Ksu getById(@PathVariable("id") Long id);

    @PostMapping("api/ksus/getBySpecificInfo")
    public Ksu getBySpecificInfo(@RequestBody KsuCheckInput id);

    @GetMapping("api/ksus/mabanksu/{id}")
    public Ksu ksuParMaBanKsu(@PathVariable("id") Long id);

}

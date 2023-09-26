package com.esmc.gestionCertification.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.Code;
import com.esmc.models.Ksu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "FORMATION-SERVICE", configuration = FeignClientConfiguration.class)
public interface FormationRestClient {

    @PostMapping("api/codes/save")
     Code addCode(@RequestBody Code c);
}

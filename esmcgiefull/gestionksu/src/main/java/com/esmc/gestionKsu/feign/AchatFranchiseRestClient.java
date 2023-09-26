package com.esmc.gestionKsu.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.Canton;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ACHATFRANCHISE-SERVICE", configuration = FeignClientConfiguration.class)
public interface AchatFranchiseRestClient {

    @GetMapping("/api/canton/search/{cantonId}")
    public Canton getCanton(@PathVariable("cantonId") Long cantonId);

}

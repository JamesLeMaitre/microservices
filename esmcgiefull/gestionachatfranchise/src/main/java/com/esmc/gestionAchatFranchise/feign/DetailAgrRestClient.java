package com.esmc.gestionAchatFranchise.feign;


//import com.esmc.feign.FeignClientConfiguration;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.DetailsAgr;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AGR-SERVICE", configuration = FeignClientConfiguration.class)
public interface DetailAgrRestClient {

    @GetMapping("api/detailAgr/{id}")
    public DetailsAgr getDetailAgrById(@PathVariable("id") Long id);
    @GetMapping("api/detailsagrs/activate/franchise-mode/{id}")
    public DetailsAgr activateFRanchiseMode(@PathVariable("id") Long id) ;



}

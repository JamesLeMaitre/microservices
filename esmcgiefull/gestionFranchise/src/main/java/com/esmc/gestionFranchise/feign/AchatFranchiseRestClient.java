package com.esmc.gestionFranchise.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.gestionFranchise.inputs.IdsClass;
import com.esmc.models.FranchiseReferentiel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ACHATFRANCHISE-SERVICE", configuration = FeignClientConfiguration.class)
public interface AchatFranchiseRestClient {
    @GetMapping("/api/achatFranchise/get/referencielles")
    public List<FranchiseReferentiel> listFranchiseReferenciel(@RequestBody IdsClass ids);
}



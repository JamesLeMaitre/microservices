package com.esmc.gestionPayement.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.AppUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "SECURITY-SERVICE", configuration = FeignClientConfiguration.class)
public interface SecurityRestClient {

    @GetMapping("/currentUser")
    public AppUser getCurrentUser(HttpServletRequest request);
}

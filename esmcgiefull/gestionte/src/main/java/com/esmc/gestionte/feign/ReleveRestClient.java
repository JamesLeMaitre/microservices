package com.esmc.gestionte.feign;

import com.esmc.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "MEMBRE-SERVICE", configuration = FeignClientConfiguration.class)
public interface ReleveRestClient {
}

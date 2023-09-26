package com.esmc.gestionAvr.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.Categorie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "contrat", url = "192.168.17.121:8080/", configuration = FeignClientConfiguration.class)
public interface ContratClient {

    @RequestMapping(method = RequestMethod.GET, value = "contrat/getById/1")
    public Categorie getContrat();
}

package com.esmc.gestionAvr.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.Categorie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "categ", url = "192.168.17.121:8080/", configuration = FeignClientConfiguration.class)
public interface CategorieClient {

    @RequestMapping(method = RequestMethod.GET, value = "categories/getById/1")
    Categorie getCategories();

}

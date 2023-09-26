package com.esmc.gestionte.feign;

import com.esmc.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "AVR-SERVICE", configuration = FeignClientConfiguration.class)
public interface AvrRestClient {

    @GetMapping("api/avrs/createSupportMarchandAvr/detailAgrVente/{idDetailAgrVente}/quantity/{quantite}/detailAgrAcheteur/{idDetailAgrAcheteur}/montant/{montant}/produit/{produit}")
    public Boolean createSupportMarchandAvr(@PathVariable("idDetailAgrVente") Long idDetailAgrVente,@PathVariable("quantite")  int quantite, @PathVariable("idDetailAgrAcheteur")  Long idDetailAgrAchat,@PathVariable("montant")  Double montant,@PathVariable("produit")  String produit);
}

package com.esmc.gestionKsu.feign;

import com.esmc.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AVR-SERVICE", configuration = FeignClientConfiguration.class)
public interface AvrRestClient {

    @GetMapping("api/avrs/createSupportMarchandAvrV4/detailAgrVente/{idDetailAgrVente}/posteRecepteur/{posteRecepteur}/quantity/{quantite}/detailAgrAcheteur/{idDetailAgrAcheteur}/montant/{montant}/produit/{produit}")
    public Boolean createSupportMarchandAvrV3(@PathVariable("idDetailAgrVente") Long idDetailAgrVente,
                                              @PathVariable("posteRecepteur")  Long posteRecepteur,
                                              @PathVariable("quantite")  int quantite,
                                              @PathVariable("idDetailAgrAcheteur")  Long idDetailAgrAchat,
                                              @PathVariable("montant")  Double montant,
                                              @PathVariable("produit")  String produit);
}

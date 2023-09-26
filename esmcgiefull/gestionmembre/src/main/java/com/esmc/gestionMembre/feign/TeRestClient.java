package com.esmc.gestionMembre.feign;

import com.esmc.models.DetailSMEnchange;
import com.esmc.models.PassifPresentielle;
import org.springframework.cloud.openfeign.FeignClient;
import com.esmc.feign.FeignClientConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "TE-SERVICE", configuration = FeignClientConfiguration.class)
public interface TeRestClient {

//    @GetMapping("api/detailSMEnchange/renitialisationBCI/montant/{montant}/idTe/{idTe}")
//    public DetailSMEnchange renitialisationBCI(@PathVariable("montant") Double montant, @PathVariable ("idTe") Long idTe);

    @GetMapping("api/passifPresentielle/code/{code}")
    public PassifPresentielle getPassifPrentielleByCodeQrOrNumRef(@PathVariable ("code") String code);

    @GetMapping("api/detailSMEnchange/renitialisationPassif/montant/{montant}/idTe/{idTe}")
    public DetailSMEnchange renitialisationPassif(@PathVariable ("montant") Double montant,@PathVariable ("idTe") Long idTe);

    @GetMapping("api/detailSMEnchange/renitialisationBCI/montant/{montant}/idTe/{idTe}")
    public DetailSMEnchange renitialisationBCI(@PathVariable ("montant") Double montant,@PathVariable ("idTe") Long idTe);
}

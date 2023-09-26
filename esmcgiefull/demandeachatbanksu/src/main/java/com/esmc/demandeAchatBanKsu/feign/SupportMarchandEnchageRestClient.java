package com.esmc.demandeAchatBanKsu.feign;


import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.SupportMarchandEnchage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TE-SERVICE", configuration = FeignClientConfiguration.class)
public interface SupportMarchandEnchageRestClient {

    @GetMapping("/api/supportMarchandEnchage/findByLibelle/{libelle}")
    public SupportMarchandEnchage supportMarchandEnchageParLibelle(@PathVariable("libelle") String libelle);

    @GetMapping("api/detailSMEnchange/debitSm_pour_achat_ksu/sm/{idSm}/montant/{montant}/refer/{refer}")
    public Object debitSMPourAchatKsu(@PathVariable("idSm") Long idSm,  @PathVariable("montant") Double montant, @PathVariable("refer") Long refer);
}

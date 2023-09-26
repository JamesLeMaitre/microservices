package com.esmc.gestionPayement.feign;

import com.esmc.feign.FeignClientConfiguration;

import com.esmc.models.SupportMarchandEnchage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@FeignClient(name = "TE-SERVICE", configuration = FeignClientConfiguration.class)
public interface TeRestClient {
    @GetMapping("api/detailSMEnchange/approvisionnement/montant/{montant}/idTe/{idTe}")
    public void approvisionnementBAn(@PathVariable("montant") double montant, @PathVariable("idTe") long idTe);

    @PostMapping("api/detailSMEnchange/change/selected/status/{selectElementId}")
    public void useSelectedElement(@PathVariable("selectElementId") Long selectElementId);

    @GetMapping("api/detailSMEnchange/change/correct-change-selected/status/{selectElementId}/{status}")
    public void useChangeSelectedElement(@PathVariable("selectElementId") Long selectElementId, @PathVariable("status") int status);

    @GetMapping("api/detailSMEnchange/te/{idTe}/montant/{montant}")
    public void mutationProcessusMevBan(@PathVariable("idTe") Long idTe, @PathVariable("montant") Double montant);

    @GetMapping("api/detailSMEnchange/transaction/{idTransaction}")
    public void generatedCircuitMevBGCs(@PathVariable("idTransaction") Long idTransaction);

    @GetMapping("transactionAdmin/{idTransaction}")
    public void mutationMevMABAn(@PathVariable("idTransaction") Long idTransaction);

    @GetMapping("api/supportMarchandEnchage/getAll")
    public List<SupportMarchandEnchage> listsupportMarchand();

    @GetMapping("api/detailSMEnchange/decaissement_mprg/{idTrans}")
    public Object mutationMPRgOPIBpsdv(@PathVariable("idTrans") Long idTrans);

    @GetMapping("api/detailSMEnchange/bpsd_ev/{idTransaction}")
    public Object gennerateMev(@PathVariable("idTransaction") Long idTransaction);
}

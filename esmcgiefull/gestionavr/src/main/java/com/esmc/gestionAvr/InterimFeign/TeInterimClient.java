package com.esmc.gestionAvr.InterimFeign;

import com.esmc.gestionAvr.cronUtilities.DefaultClient;
import com.esmc.models.TerminalEchange;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Component
public class TeInterimClient extends DefaultClient {
    public String defaultPath = "TE-SERVICE/";

    public TerminalEchange getTeByDetailAgr(@PathVariable("id") Long id) {
        String url = String.format("api/terminalEchange/detail_agrte/%s", id);
        return (TerminalEchange) this.GetRequest(defaultPath + url, new ParameterizedTypeReference<TerminalEchange>() {
        });
    }

    public Double cumuleAvailableAmountInKsuWithTeByTypeSupportSmEchange(@PathVariable("type") String type, @PathVariable("te") Long te) {
        String url = String.format("api/detailSMEnchange/cumule-amount-byksu/type/%s/te/%s", type, te);
        return (Double) this.GetRequest(defaultPath + url, new ParameterizedTypeReference<Double>() {
        });
    }


    public Double checkSmTotalCurrent(@PathVariable("idSm") Long idSm, @PathVariable("idTe") Long idTe) {
        String url = String.format("api/detailSMEnchange/montant_total_sm/sm/%s/te/%s", idSm, idTe);
        return (Double) this.GetRequest(defaultPath + url, new ParameterizedTypeReference<Double>() {
        });
    }

    public Boolean mutationPourAchatFifo(@PathVariable("idTeAcheteur") Long idTeAcheteur, @PathVariable("idTeVendeur") Long idTeVendeur, @PathVariable("montant") Double montant) {
        String url = String.format("api/detailSMEnchange/mutation_fifo_acheteur/te_acheteur/%s/te_vendeur/%s/montant/%s", idTeAcheteur, idTeVendeur, montant);
        return (Boolean) this.GetRequest(defaultPath + url, new ParameterizedTypeReference<Boolean>() {
        });
    }

    public Boolean mutationPourAchatFifoAdmin(@PathVariable("idTeAcheteur") Long idTeAcheteur, @PathVariable("idTeVendeur") Long idTeVendeur, @PathVariable("montant") Double montant) {
        String url = String.format("api/detailSMEnchange/mutation_fifo_acheteur_admin/te_acheteur/%s/te_vendeur/%s/montant/%s", idTeAcheteur, idTeVendeur, montant);
        return (Boolean) this.GetRequest(defaultPath + url, new ParameterizedTypeReference<Boolean>() {
        });
    }


    public void registerNewDecaissement(@PathVariable("montant") Double montant, @PathVariable("type") String type, @PathVariable("teDe") Long te) {
        String url = String.format("api/detailSMEnchange/decaissement/%s/type/%s/teDe/%s", montant, type, te);
        this.GetRequest(defaultPath + url, new ParameterizedTypeReference<Object>() {
        });
    }

    @GetMapping("api/detailSMEnchange/encaissement/{montant}/type/{type}/teEn/{teEn}")
    public void registerEncaissement(@PathVariable("montant") Double montant, @PathVariable("type") String type, @PathVariable("teEn") Long teEn) {
        String url = String.format("api/detailSMEnchange/decaissement/%s/type/%s/teDe/%s", montant, type, teEn);
        this.GetRequest(defaultPath + url, new ParameterizedTypeReference<Object>() {
        });
    }

}

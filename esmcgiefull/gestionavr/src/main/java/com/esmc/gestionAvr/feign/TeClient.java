package com.esmc.gestionAvr.feign;


import com.esmc.feign.FeignClientCronConfiguration;
import com.esmc.models.DetailSMEnchange;
import com.esmc.models.TerminalEchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "TE-SERVICE", configuration = FeignClientCronConfiguration.class)
public interface TeClient {
    @GetMapping("api/promotion/bci-ban/{montant}")
    public Double getConversionBCIBAN(@PathVariable("montant") Double montant) ;

    @GetMapping("api/promotion/ban-bci/{montant}")
    public Double getConversionBANBCI(@PathVariable("montant") Double montant) ;
    @GetMapping("api/detailSMEnchange/encaissement/{montant}/type/{type}/teEn/{teEn}")
    public void registerEncaissement(@PathVariable ("montant") Double montant, @PathVariable ("type") String type, @PathVariable ("teEn") Long teEn ) ;

    @GetMapping("api/detailSMEnchange/decaissement/{montant}/type/{type}/teDe/{teDe}")
    public void registerNewDecaissement(@PathVariable ("montant") Double montant,@PathVariable ("type") String type,@PathVariable ("teDe") Long te) ;

    @GetMapping( value = "api/terminalEchange/detail_agrte/{id}")
    public TerminalEchange getTeByDetailAgr( @PathVariable ("id") Long id);

    @GetMapping("api/detailSMEnchange/cumule-amount-byksu/type/{type}/te/{te}")
    public Double cumuleAvailableAmountInKsuWithTeByTypeSupportSmEchange(@PathVariable("type") String type,@PathVariable ("te") Long te);

    @GetMapping("api/detailSMEnchange/achat_desendettement/te/{idTe}")
    public Boolean achatDesendettement(@PathVariable("idTe") Long idTe);

    @GetMapping("api/detailSMEnchange/amount/te/{idTe}")
    public Double amountBciRiTe(@PathVariable("idTe") Long idTe);

    @GetMapping("api/detailSMEnchange/prelevent_produit/te/{idTe}/montant/{montant}")
    public DetailSMEnchange mutationFondDisponibleUsed(@PathVariable("idTe") Long idTe, @PathVariable("montant") Double montant);

    @GetMapping("api/detailSMEnchange/montant_total_sm/sm/{idSm}/te/{idTe}")
    public Double checkSmTotalCurrent(@PathVariable("idSm") Long idSm, @PathVariable("idTe") Long idTe) ;

    @GetMapping("api/detailSMEnchange/mutation_fifo_acheteur/te_acheteur/{idTeAcheteur}/te_vendeur/{idTeVendeur}/montant/{montant}")
    public Boolean mutationPourAchatFifo(@PathVariable("idTeAcheteur") Long idTeAcheteur, @PathVariable("idTeVendeur") Long idTeVendeur, @PathVariable("montant") Double montant);

    @GetMapping("api/detailSMEnchange/mutation_fifo_acheteur_admin/te_acheteur/{idTeAcheteur}/te_vendeur/{idTeVendeur}/montant/{montant}")
    public Boolean mutationPourAchatFifoAdmin(@PathVariable("idTeAcheteur") Long idTeAcheteur, @PathVariable("idTeVendeur") Long idTeVendeur, @PathVariable("montant") Double montant);

    @GetMapping("api/detailSMEnchange/depot_interim/idSm/{idSm}/te/{idTe}/montant/{montant}")
    public Object depotAInterim(@PathVariable("idSm") Long idIdSm, @PathVariable("idTe") Long idTe, @PathVariable("montant") Double montant) ;
}

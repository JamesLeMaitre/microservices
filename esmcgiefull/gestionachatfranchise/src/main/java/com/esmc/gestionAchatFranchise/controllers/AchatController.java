package com.esmc.gestionAchatFranchise.controllers;

import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import com.esmc.gestionAchatFranchise.entities.Canton;
import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.franchise.fill.FranchiseFillChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.franchise.flbOe.FranchiseFlbOeChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.franchise.refereniel.FranchiseReferentiel;
import com.esmc.gestionAchatFranchise.feign.DetailAgrRestClient;
import com.esmc.gestionAchatFranchise.inputs.achatFranchiseInput;
import com.esmc.gestionAchatFranchise.servicesinterfaces.AchatServiceInterface;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.fill.FillChaineDistributionInterface;
import com.esmc.input.IdsClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("/api/achatFranchise")
public class AchatController extends DataFormatter<String> {

    @Autowired
    private AchatServiceInterface commonInterface;

    @Autowired
    private DetailAgrRestClient detailAgrRestClient;

    @PostMapping("/decoupage/geographique")
    public Object achat_franchise_decoupage_geographique(@RequestBody() achatFranchiseInput data){
        try {
            Boolean status = commonInterface.achat_franchise_decoupage_geographique(data);
            if(status){
                detailAgrRestClient.activateFRanchiseMode(data.getIdDetailAgr());
                return  renderStringData(true, "Successfully operation","Buy Franchise ");
            }else{
                return  renderStringData(false,"Error while processing" ,"");
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

    @PostMapping("/decoupage/geographique/check")
    public Object achat_franchise_decoupage_geographique_check(@RequestBody() achatFranchiseInput data){
        try {
            Boolean status = commonInterface.achat_franchise_decoupage_geographique_check(data);
            if(status ==true){
                return  renderStringData(true, "Successfull operation","Buy Franchise ");
            }else{
                return  renderStringData(false,"Error while procssing" ,"");
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }

    }


    @PostMapping("/decoupage/centre")
    public Object achat_franchise_decoupage_centre(@RequestBody() achatFranchiseInput data){
       try {
            Boolean status = commonInterface.achat_franchise_decoupage_centre(data);
            if(status){
                detailAgrRestClient.activateFRanchiseMode(data.getIdDetailAgr());
                return  renderStringData(true, "Successfully operation","Buy Franchise ");
            }else{
                return  renderStringData(false,"Error while processing" ,"");
            }
       } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PostMapping("/decoupage/centre/get-centre/stage/{stage}/franchise/{idFranchise}")
    public Object achat_franchise_decoupage_centre_getcentre(@PathVariable("stage")int stage,@PathVariable("idFranchise") Long idFranchise){
        //try {
            Canton canton = commonInterface.achat_franchise_decoupage_centre_getcentre(stage,idFranchise);
            if(canton == null){
                return  renderStringData(false,"Error while processing" ,"");
            }
            DataFormatter<Canton> dataFormatter = new DataFormatter<Canton>();
            return dataFormatter.renderData(false,canton ,"Centre");
       /* } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }*/
    }

    @PostMapping("/decoupage/centre/check")
    public Object achat_franchise_decoupage_centre_check(@RequestBody() achatFranchiseInput data){
        try {
            Boolean status = commonInterface.achat_franchise_decoupage_centre_check(data);
            if(status){
                return  renderStringData(true, "Successfully operation","Buy Franchise ");
            }else{
                return  renderStringData(false,"Error while processing" ,"");
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }


    @GetMapping("/distributor/available/{stage}")
    public Object distributor_available(@PathVariable("stage") Long stage){
        try {
            if(stage == 1){
                DataFormatter<FranchiseFillChaineDistribution> values = new DataFormatter<>();
                return  values.renderDataArray(true, commonInterface.distributor_fill_available(),"Successfull  operation");
            }else {
                DataFormatter<FranchiseFlbOeChaineDistribution> values = new DataFormatter<>();
                return  values.renderDataArray(true, commonInterface.distributor_flboe_available(),"Successfull operation");
            }
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

    @GetMapping("/get/referencielles/{ids}")
    public List<FranchiseReferentiel> listFranchiseReferenciel(@PathVariable("ids") List<Long> ids){
        return commonInterface.listFranchiseReferenciel(ids);
    }

    @PostMapping("get/referencielles")
    public List<FranchiseReferentiel> listFranchiseReferenciel(@RequestBody IdsClass ids) {
        return commonInterface.listFranchiseReferenciel(ids.getIds());
    }



}

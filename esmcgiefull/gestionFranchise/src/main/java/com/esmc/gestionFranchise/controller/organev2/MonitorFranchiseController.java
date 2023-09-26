package com.esmc.gestionFranchise.controller.organev2;

import com.esmc.gestionFranchise.entities.AgentRecruteur;
import com.esmc.gestionFranchise.entities.organev2.AgentPoste;
import com.esmc.gestionFranchise.entities.organev2.AgentPosteView;
import com.esmc.gestionFranchise.entities.organev2.PosteTypeSupport;
import com.esmc.gestionFranchise.inputs.AgentRecruteurInput;
import com.esmc.gestionFranchise.services.organev2.MonitorFranchiseService;
import com.esmc.models.FranchiseReferentiel;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;
import utiles.Messagerie;

import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@RequestMapping("api/monitor/")
public class MonitorFranchiseController extends Messagerie {

    @Autowired
    MonitorFranchiseService monitorFranchiseService;

    @PostMapping("franchise-master/{idFranchise}/agent-generator/{idPoste}")
    public Object franchise_master_generator(@PathVariable Long idFranchise, @PathVariable Long idPoste){
        DataFormatter<AgentPoste> dataFormatter = new DataFormatter<>();
        try {
            AgentPoste data  = monitorFranchiseService.franchise_master_generator(idFranchise,idPoste);
            return  dataFormatter.renderData(true, data,"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  dataFormatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PostMapping("franchise-masterv2")
    public Object franchise_master_generator(@RequestBody AgentRecruteurInput data){
        DataFormatter<AgentPoste> dataFormatter = new DataFormatter<>();
        try {
            AgentPoste datas  = monitorFranchiseService.franchise_master_generatorv2(data.getIdDetailAgrFranchiser(),data.getIdPoste(),data.getIdAgr());
            if(datas == null){
                return dataFormatter.renderStringData(false,"AGR no Franchiser","Operation not Done");
            }
            return  dataFormatter.renderData(true, datas,"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  dataFormatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PostMapping("getMaster_franchise-masterv2")
    public Object get_franchise_master_generator(@RequestBody AgentRecruteurInput data){
        DataFormatter<AgentRecruteur> dataFormatter = new DataFormatter<>();
        try {
            AgentRecruteur datas  = monitorFranchiseService.get(data.getIdAgr(),data.getIdPoste(),data.getIdDetailAgrFranchiser());
            if(datas == null){
                return dataFormatter.renderStringData(false,"No Code Pouvoir Faire","Operation not Done");
            }
            return  dataFormatter.renderData(true, datas,"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  dataFormatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PostMapping("franchise-master/affectation-agent/{idAgent}/ksu/{idKsu}")
    public Object franchise_affectation_agent(@PathVariable Long idAgent, @PathVariable Long idKsu){
        DataFormatter<AgentPoste> dataFormatter = new DataFormatter<>();
        try {
            return  dataFormatter.renderData(true, monitorFranchiseService.franchise_affectation_agent(idAgent,idKsu),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  dataFormatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("franchise-master/{idFranchise}/agents")
    public Object list_franchise_affectation_agent(@PathVariable Long idFranchise){
        DataFormatter<AgentPoste> dataFormatter = new DataFormatter<>();
        try {
            return  dataFormatter.renderDataArray(true, monitorFranchiseService.list_franchise_affectation_agent(idFranchise),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  dataFormatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("franchise-master/franchise-by-detail-agr/{idDetailAgr}")
    public Object list_franchise_by_ksu(@PathVariable Long idDetailAgr){
        DataFormatter<FranchiseReferentiel> dataFormatter = new DataFormatter<>();
        try {
            return  dataFormatter.renderDataArray(true, monitorFranchiseService.list_franchise_by_idDetailAgr(idDetailAgr),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  dataFormatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("franchise-master/franchise-by-codePouvoirFaire/{codePouvoirFaire}")
    public Object get_franchise_by_codePouvoirFaire(@PathVariable String codePouvoirFaire){
        DataFormatter<AgentPoste> dataFormatter = new DataFormatter<>();
        try {
            return  dataFormatter.renderData(true, monitorFranchiseService.get_franchise_by_codePouvoirFaire(codePouvoirFaire),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  dataFormatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("franchise-master/getCodePouvoirFaireByIdPoste/{idPoste}")
    public Object getByPoste(@PathVariable Long idPoste){
        DataFormatter<AgentRecruteur> dataFormatter = new DataFormatter<>();
        try {
            return  dataFormatter.renderDataArray(true, monitorFranchiseService.getCodePouvoirFaire(idPoste),"Get Successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  dataFormatter.renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
}

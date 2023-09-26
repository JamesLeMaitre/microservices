package com.esmc.gestionFranchise.controller.organev2;

import com.esmc.gestionFranchise.entities.organev2.AgentPoste;
import com.esmc.gestionFranchise.servicesInterface.organev2.AgentPosteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("api/agentPoste/")
public class AgentPosteController extends DataFormatter<AgentPoste> {
    @Autowired
    private  AgentPosteService agentPosteService;

    @GetMapping("list/intervenant/agent/tdep/{id}")
    public Object list_intervenant_agent(@PathVariable Long id){
        try {
            return  renderData(true, agentPosteService.getAgentPosteById(id),"list intervenant ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("list_agent_poste/{id}")
    public List<AgentPoste> listAgentBosteByIdDetailAgrFranchise(@PathVariable Long id){
        try {
            return  agentPosteService.agentPosteListByDetailAgrFranchise(id);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
           return null;
        }
    }

    @PostMapping("add")
    public Object create(@RequestBody() AgentPoste data){
        try {
            return  renderData(true, agentPosteService.create(data),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

    @PutMapping(value = "update/{id}")
    public Object update(@PathVariable Long id, @RequestBody AgentPoste data) {
        try {
            if( agentPosteService.getAgentPosteById(id)==null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            return  renderData(true, agentPosteService.update(id,data),"update done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("list")
    public Object List(){
        try {
            List<AgentPoste> items = agentPosteService.getAll();
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping("by/id/{id}")
    public Object getById(@PathVariable("id") Long id){
        try {
            AgentPoste item = agentPosteService.getAgentPosteById(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            return  renderData(true,item,"Element found");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("byIdDetailAgr/{id}")
    public Object getByIdDetailAgr(@PathVariable("id") Long id){
        try {
            List<AgentPoste> item = agentPosteService.byIdDetailAgr(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            return  renderDataArray(true,item,"Element found");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @DeleteMapping("delete/{id}")
    public Object delete(@PathVariable("id") Long id){
        try {
            AgentPoste item = agentPosteService.getAgentPosteById(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            agentPosteService.delete(id);
            return  renderStringData(true,"Delete successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
}

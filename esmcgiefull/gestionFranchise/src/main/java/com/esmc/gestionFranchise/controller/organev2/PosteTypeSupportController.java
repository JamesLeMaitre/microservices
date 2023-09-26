package com.esmc.gestionFranchise.controller.organev2;

import com.esmc.gestionFranchise.entities.organev2.PosteTypeSupport;
import com.esmc.gestionFranchise.servicesInterface.organev2.PosteTypeSupportService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("api/posteTypeSupport/")
public class PosteTypeSupportController extends DataFormatter<PosteTypeSupport> {
    @Autowired
    private  PosteTypeSupportService posteTypeSupportService;

   @PostMapping("add/idTypeSupport/{idTypeSupport}/idPosteSender/{idPosteSender}/idPosteReceiver/{idPosteReceiver}")
    public Object create(@RequestBody() PosteTypeSupport data, @PathVariable Long idPosteReceiver, @PathVariable Long idTypeSupport, @PathVariable Long idPosteSender){
        try {
            return  renderData(true, posteTypeSupportService.create(data,idTypeSupport,idPosteSender,idPosteReceiver),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

  @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody PosteTypeSupport data) {
        try {
            if( posteTypeSupportService.getPosteTypeSupportById(id)==null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            return  renderData(true, posteTypeSupportService.update(id,data),"update done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

   @GetMapping("list")
    public Object List(){
       DataFormatter<JSONObject> dataFormatter = new DataFormatter<>();
        try {
            List<JSONObject> items = posteTypeSupportService.getAll();
            return  dataFormatter.renderDataArray(true, items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("by/id/{id}")
    public Object getByTypeSupportId(@PathVariable("id") Long id){
        try {
            PosteTypeSupport item = posteTypeSupportService.getPosteTypeSupportById(id);
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

    @DeleteMapping("delete/{id}")
    public Object deleteTypeSupport(@PathVariable("id") Long id){
        try {
            PosteTypeSupport item = posteTypeSupportService.getPosteTypeSupportById(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            posteTypeSupportService.delete(id);
            return  renderStringData(true,"Delete successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("enable/{id}")
    public Object enable(@PathVariable Long id) {
        try {
            if( posteTypeSupportService.getPosteTypeSupportById(id)==null){
                return  renderStringData(false,"Error while procssing" ,"item not found");
            }
            return  renderData(true, posteTypeSupportService.enable(id),"enable done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("disable/{id}")
    public Object disable(@PathVariable Long id) {
        try {
            if( posteTypeSupportService.getPosteTypeSupportById(id)==null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            return  renderData(true, posteTypeSupportService.disable(id),"disable done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("listByIntervenant/idReceiver/{idReceiver}/idSender/{idSender}/idTdep/{idTdep}")
    public Object listByIntervenant(@PathVariable Long idReceiver, @PathVariable Long idSender, @PathVariable Long idTdep){
       DataFormatter<JSONObject> dataFormatter = new DataFormatter<>();
        try {
           List<JSONObject> items =  posteTypeSupportService.getPosteTypeSupportByIntervenants(idReceiver,idSender,idTdep);
            return  dataFormatter.renderDataArray(true, items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
}

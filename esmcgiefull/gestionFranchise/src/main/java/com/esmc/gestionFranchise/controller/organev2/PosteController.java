package com.esmc.gestionFranchise.controller.organev2;



import com.esmc.gestionFranchise.datafomater.ImportData;
import com.esmc.gestionFranchise.datafomater.PosteJSON;
import com.esmc.gestionFranchise.datafomater.StrufctureJSON;
import com.esmc.gestionFranchise.entities.organev2.Poste;
import com.esmc.gestionFranchise.servicesInterface.organev2.PosteService;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("api/posteOrgane/")
public class PosteController  extends DataFormatter<Poste> {

   @Autowired
    private PosteService posteService;

   private final String postePrefix = "Poste";

    @PostMapping("add")
    public Object create(@RequestBody() Poste data){
        try {
            return  renderData(true, posteService.create(data),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }

    }


    @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody Poste data) {
        try {
            if( posteService.getById(id)==null){
                return  renderStringData(false,"Error while procssing" ,"item not found");
            }
            return  renderData(true, posteService.update(id,data),"update done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PutMapping(value = "enable/idPoste/{id}")
    public Object enable(@PathVariable Long id) {
        try {
            if( posteService.getById(id)==null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            return  renderData(true, posteService.enable(id),"enable done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PutMapping(value = "disable/idPoste/{id}")
    public Object disable(@PathVariable Long id) {
        try {
            if( posteService.getById(id)==null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            return  renderData(true, posteService.disable(id),"disable done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
    @Cacheable(value = postePrefix, unless = "#result = null")
    @GetMapping("list")
    public Object List(){
        try {
            List<Poste> items = posteService.getAll();
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @Cacheable(value = postePrefix, unless = "#result = null")
    @GetMapping("listv3")
    public Object ListV3(){
        DataFormatter<Object> dataFormatter = new DataFormatter<>();
        try {
            Object items = posteService.getAllV2();
            return  dataFormatter.renderData(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("listv2/{idx}/{idz}")
    public Object Listv2(@PathVariable("idx")int idx,@PathVariable("idz")int idz){
        try {
          DataFormatter<Page> dataFormatter = new DataFormatter<>();
          DataFormatter<JSONObject> jsonObjectDataFormatter = new DataFormatter<>();
            Page<Poste> items = posteService.getAllv2(idz,idz);
          JSONObject jsonObject = new JSONObject();


            return  dataFormatter.renderData(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("listByParent/{idPoste}")
    public Object listByParent(@PathVariable Long idPoste){
        try {
            List<Poste> items = posteService.getPosteByParent(idPoste);
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


    @GetMapping("by/id/{id}")
    public Object getById(@PathVariable("id") Long id){
        try {
            Poste item = posteService.getById(id);
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



    @GetMapping("getWithout/id/{id}")
    public Object getWithout(@PathVariable("id") Long id){
        try {
            List<Poste> item = posteService.getWithout(id);
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
            Poste item = posteService.getById(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            posteService.delete(id);
            return  renderStringData(true,"Delete successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping("by/typePoste/{id}")
    public Object List(@PathVariable("id") Long id){
        try {
            List<Poste> items = posteService.postByIdTypePoste(id);
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }



    @PostMapping("/load/data")
    public Object loadResource(@RequestBody ImportData<PosteJSON[]> data) throws IOException {

        try {
            posteService.loadPost(data.getData());
            return  renderStringData(true,"init successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }



}

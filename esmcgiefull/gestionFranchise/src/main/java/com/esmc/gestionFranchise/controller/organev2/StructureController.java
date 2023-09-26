package com.esmc.gestionFranchise.controller.organev2;


import com.esmc.gestionFranchise.datafomater.ImportData;
import com.esmc.gestionFranchise.datafomater.StrufctureJSON;
import com.esmc.gestionFranchise.entities.organev2.Structure;
import com.esmc.gestionFranchise.servicesInterface.organev2.StructureService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;


@RestController
@RequestMapping("/api/structure/")
public class StructureController extends DataFormatter<Structure>  {


    private ObjectMapper mapper;
    private ResourceLoader resourceLoader;


    public StructureController(ObjectMapper mapper, ResourceLoader resourceLoader) {
        this.mapper = mapper;
        this.resourceLoader = resourceLoader;
    }

    @Autowired
    private StructureService structureService;

    @PostMapping("add")
    public Object create(@RequestBody() Structure data){
        try {
            return  renderData(true, structureService.create(data),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }


    @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody Structure data) {
        try {
            if( structureService.getById(id)==null){
                return  renderStringData(false,"Error while procssing" ,"item not found");
            }
            return  renderData(true, structureService.update(id,data),"update done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping(value = "enable/{id}")
    public Object enable(@PathVariable Long id, @RequestBody Structure data) {
        try {
            if( structureService.getById(id)==null){
                return  renderStringData(false,"Error while procssing" ,"item not found");
            }
            return  renderData(true, structureService.enable(id),"enable done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping(value = "disable/{id}")
    public Object disable(@PathVariable Long id, @RequestBody Structure data) {
        try {
            if( structureService.getById(id)==null){
                return  renderStringData(false,"Error while procssing" ,"item not found");
            }
            return  renderData(true, structureService.disable(id),"disable done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }
    @GetMapping("list")
    public Object List(){
        try {
            List<Structure> items = structureService.getAll();
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
            Structure item = structureService.getById(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            return  renderData(true,item,"Element found");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }


    @DeleteMapping("delete/{id}")
    public Object delete(@PathVariable("id") Long id){
        try {
            Structure item = structureService.getById(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            structureService.delete(id);
            return  renderStringData(true,"Delete successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }




    @PostMapping("load/data")
    public Object loadResource(@RequestBody ImportData<StrufctureJSON[]> data) throws IOException {

        try {
            structureService.loadResource(data.getData());
            return  renderStringData(true,"init successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }
}

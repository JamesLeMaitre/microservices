package com.esmc.gestionAchatFranchise.controllers.felm.flbose;

import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseAgenceOdd;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flbose.FlbOseAgenceOddInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping(value="/api/flb/ose/agenceOdd")
public class FlbOseAgenceOddController extends DataFormatter<FlbOseAgenceOdd> {
    @Autowired
    private FlbOseAgenceOddInterface commonInterface;

    @PostMapping("add")
    public Object create(@RequestBody() FlbOseAgenceOdd data){
        try {
            return  renderData(true, commonInterface.create(data),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }

    }


    @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody FlbOseAgenceOdd data) {
        try {
            if( commonInterface.getById(id)==null){
                return  renderStringData(false,"Error while procssing" ,"item not found");
            }
            return  renderData(true, commonInterface.update(id,data),"update done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping(value = "enable/{id}")
    public Object enable(@PathVariable Long id, @RequestBody FlbOseAgenceOdd data) {
        try {
            if( commonInterface.getById(id)==null){
                return  renderStringData(false,"Error while procssing" ,"item not found");
            }
            return  renderData(true, commonInterface.enable(id),"enable done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping(value = "disable/{id}")
    public Object disable(@PathVariable Long id, @RequestBody FlbOseAgenceOdd data) {
        try {
            if( commonInterface.getById(id)==null){
                return  renderStringData(false,"Error while procssing" ,"item not found");
            }
            return  renderData(true, commonInterface.disable(id),"disable done successfully");
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
            List<FlbOseAgenceOdd> items = commonInterface.getAll();
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
            FlbOseAgenceOdd item = commonInterface.getById(id);
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
            FlbOseAgenceOdd item = commonInterface.getById(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            commonInterface.delete(id);
            return  renderStringData(true,"Delete successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }
}

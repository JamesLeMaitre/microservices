package com.esmc.gestionAchatFranchise.controllers.felm.flbose;

import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseCible;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flbose.FlbOseCibleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;


@RestController
@RequestMapping(value="/api/flb/ose/cible")
public class FlbOseCibleController extends DataFormatter<FlbOseCible> {
    @Autowired
    private FlbOseCibleInterface commonInterface;

    @PostMapping("add")
    public Object create(@RequestBody() FlbOseCible data){
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
    public Object update(@PathVariable Long id, @RequestBody FlbOseCible data) {
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
    public Object enable(@PathVariable Long id, @RequestBody FlbOseCible data) {
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
    public Object disable(@PathVariable Long id, @RequestBody FlbOseCible data) {
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
            List<FlbOseCible> items = commonInterface.getAll();
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
            FlbOseCible item = commonInterface.getById(id);
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

    @GetMapping("by/agenceOdd/{id}")
    public Object ListByAgenceOddId(@PathVariable("id") Long id){
        try {
            List<FlbOseCible> items = commonInterface.getListByAgenceOddId(id);
            return  renderDataArray(true,items,"list of element");
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
            FlbOseCible item = commonInterface.getById(id);
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

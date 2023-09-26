package com.esmc.gestionAvr.controllers;


  import com.esmc.gestionAvr.entities.FifoHistory;
import com.esmc.gestionAvr.servicesInterfaces.FifoHistoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;



@RestController
@RequestMapping(value="/api/fifoHistory")
public class FifoHistoryController extends DataFormatter<FifoHistory> {
    @Autowired
    private FifoHistoryInterface commonInterface;


    @GetMapping("list")
    public Object List(){
        try {
            List<FifoHistory> items = commonInterface.getAll();
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
            FifoHistory item = commonInterface.getById(id);
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

    @GetMapping("by/type/{isBuy}")
    public Object ListByTypeId(@PathVariable("isBuy") Boolean isBuy){
        try {
            List<FifoHistory> items = commonInterface.getListByType(isBuy);
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping("by/detailAgr/{id}")
    public Object ListByIdDetailAgr(@PathVariable("id") Long id){
        try {
            List<FifoHistory> items = commonInterface.getListByIdDetailAgr(id);
            return  renderDataArray(true,items,"list of element by detailAgrr");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }


}

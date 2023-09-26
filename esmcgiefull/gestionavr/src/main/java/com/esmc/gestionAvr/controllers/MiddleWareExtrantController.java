package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.Extrant;
import com.esmc.gestionAvr.entities.MiddleWareExtrant;
import com.esmc.gestionAvr.inputs.MInput;
import com.esmc.gestionAvr.services.MiddleWareExtrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import javax.ws.rs.Path;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping(path = "api/mdx/")
public class MiddleWareExtrantController  extends DataFormatter<MiddleWareExtrant> {

    @Autowired
    private MiddleWareExtrantService mdx;
    @PutMapping("edit/idExtrant/idIntervenant")
    public Object updateExtrants(@RequestBody MInput dt) {
        try {
            return  renderData(true, mdx.updateMedia(dt),"Update Successfully !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("getTour/intervenant/{id}")
    public Object listeIntrants(@PathVariable Long id) {
        try {
            List<MiddleWareExtrant> items = mdx.listIntrant(id);
            if(items.isEmpty()){
                renderStringData(false,"","No Tour for this guy!");
            }
            return  renderDataArray(true,items ,"List Intrant Successfully !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("get-intrant-extrant/idIntervenant/{id}")
    public Object listeExtrant(@PathVariable Long id) {
        DataFormatter<Extrant> d = new DataFormatter<>();
        try {
            List<Extrant> items = mdx.getByTour(id);
            if(items.isEmpty()){
                renderStringData(false,"","No Intrant orExtrant for this guy!");
            }
            return  d.renderDataArray(true,items ,"Successfully !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("get-intrant-extrant-archiver/idIntervenant/{id}")
    public Object listeExtrantArchiver(@PathVariable Long id) {
        DataFormatter<Extrant> d = new DataFormatter<>();
        try {
            List<Extrant> items = mdx.getExtrantArchiver(id);
            if(items.isEmpty()){
                renderStringData(false,"","No Data Archived !");
            }
            return  d.renderDataArray(true,items ,"Successfully !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

//    @PostMapping("archiverTour/idTour/{id}")
//    public Object archiverT(@PathVariable Long id) {
//        try {
//           MiddleWareExtrant items = mdx.archiverTour(id);
//            if(items!=null){
//                renderData(true,items,"Archive done Successfully !");
//            }
//            return  renderStringData(false,"" ,"Not Done !");
//        } catch (Exception e) {
//            StringWriter sw = new StringWriter();
//            e.printStackTrace(new PrintWriter(sw));
//            String exceptionAsString = sw.toString();
//            return  renderStringData(false,"Error while processing" ,exceptionAsString);
//        }
//    }
//
//    @PostMapping("viderTour/idTour/{id}")
//    public Object viderTour(@PathVariable Long id) {
//        try {
//            MiddleWareExtrant items = mdx.viderTour(id);
//            if(items!=null){
//                renderData(true,items,"Clean done Successfully !");
//            }
//            return  renderStringData(false,"" ,"Not Done !");
//        } catch (Exception e) {
//            StringWriter sw = new StringWriter();
//            e.printStackTrace(new PrintWriter(sw));
//            String exceptionAsString = sw.toString();
//            return  renderStringData(false,"Error while processing" ,exceptionAsString);
//        }
//    }
}

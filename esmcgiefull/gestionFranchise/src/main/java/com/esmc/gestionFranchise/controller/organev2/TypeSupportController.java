package com.esmc.gestionFranchise.controller.organev2;

import com.esmc.gestionFranchise.entities.SupportUtilise;
import com.esmc.gestionFranchise.entities.organev2.TypeSupport;
import com.esmc.gestionFranchise.servicesInterface.organev2.TypeSupportService;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;


@RestController
@RequestMapping("api/typeSupport/")
public class TypeSupportController extends DataFormatter<TypeSupport> {
    @Autowired
    private  TypeSupportService typeSupportService;

    @PostMapping("add")
    public Object create(@RequestBody() TypeSupport data){
        try {
            return  renderData(true, typeSupportService.create(data),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody TypeSupport data) {
        try {
            if( typeSupportService.getTypeSupportById(id)==null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            return  renderData(true, typeSupportService.update(data,id),"update done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("list")
    public Object List(){
        DataFormatter<JSONObject> dp =new DataFormatter<>();
        try {
            JSONObject items =  typeSupportService.getAll();
            return  dp.renderData(true,items,"list of element");
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
            TypeSupport item = typeSupportService.getTypeSupportById(id);
            if (item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");

            }
            //return item;
           // return  renderData(true,item,"Element found");
            return item;
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            //return  renderStringData(false,"Error while processing" ,exceptionAsString);
           return exceptionAsString;
        }
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<TypeSupport> getTypeSupport(@PathVariable Long id){
        return new ResponseEntity<>(   typeSupportService.getTypeSupportById(id), HttpStatus.OK);
    }

    @GetMapping("by/libelle/{id}")
    public Object getLibelle(@PathVariable("id") Long id){
        try {
            String item = typeSupportService.getLibelle(id);
            System.out.println("ITEM" +item );
            if(item == null){
                return  renderStringData(false,"TypeSupport not found or not exist" ,"Please abeg check your Code :-)");
            }
            return item;
           //return  renderStringData(true,item,"Element found");
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
            TypeSupport item = typeSupportService.getTypeSupportById(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            typeSupportService.delete(id);
            return  renderStringData(true,"Delete successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

}

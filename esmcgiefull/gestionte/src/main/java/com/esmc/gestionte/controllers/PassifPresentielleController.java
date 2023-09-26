package com.esmc.gestionte.controllers;

import com.esmc.gestionte.entities.PassifPresentielle;
import com.esmc.gestionte.inputs.SelectPassifInput;
import com.esmc.gestionte.serviceinterface.PassifPresentielleInterface;
import com.esmc.input.KsuCheckInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import javax.validation.Valid;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping(value="/api/passifPresentielle/")
public class PassifPresentielleController extends DataFormatter<PassifPresentielle> {
    @Autowired
    private PassifPresentielleInterface commonInterface;

    @PostMapping("add")
    public Object create(@RequestBody() PassifPresentielle data){
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
    public Object update(@PathVariable Long id, @RequestBody PassifPresentielle data) {
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
    public Object enable(@PathVariable Long id) {
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

    @GetMapping(value = "check/byPassifPresentiellebyNumeroRef/{CodeQrOrNumRef}/numero/{numero}")
    public Object getPassifPrentielleByCodeQrOrNumRef(@PathVariable("CodeQrOrNumRef") String CodeQrOrNumRef,@PathVariable("numero") String numero) {
        try {
            if(commonInterface.getPassifPrentielleByCodeQrOrNumRef(CodeQrOrNumRef)==null){
                return  renderStringData(false,"Error while procssing" ,"item not found");
            }
            return  renderStringData(true, commonInterface.checkPassifPrentielleByCodeQrOrNumRef(CodeQrOrNumRef, numero)+"","check by Numero Ref  done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }


    @GetMapping(value = "getPassifPresentielleCode/byKsu/{idKsu}")
    public Object getPassifPresentielleCode(@PathVariable("idKsu") Long idKsu) {
        try {
            return  renderStringData(true, commonInterface.checkPassifPrentielleTreatByKsu(idKsu)+"","passifPresential code");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }
    @PostMapping(value = "getPassifPresentielleCode/info")
    public Object getPassifPresentielleCodeByInfo(@RequestBody @Valid KsuCheckInput ksuCheckInput ) {
       try {
        String code =  commonInterface.checkPassifPrentielleTreatByInfo(ksuCheckInput);
        if( code ==null){
            return  renderStringData(false,"Error while procssing" ,"unable to execute this action. unsuficient found");
        }
        return  renderStringData(true, code,"enable done successfully");
         } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }
    @GetMapping(value = "disablePassif/byKsu/{idKsu}")
    public Object disablePassif(@PathVariable("idKsu") Long idKsu) {
        try {
            commonInterface.disable(idKsu);
            return  renderStringData(true, "","passifPresential code");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }
    @GetMapping(value = "getPassifPresentielle/byKsu/{idKsu}/code/{code}")
    public Object getPassifPresentielle(@PathVariable("idKsu") Long idKsu, @PathVariable("code") String code) {
        try {
            PassifPresentielle passifPresentielle =  commonInterface.checkValidePassifGenerateActivateCode(idKsu,code);
            if(passifPresentielle == null){
                return  renderStringData(false,"Error while proecssing" ,"unable to load information");
            }
           return  renderData(true, passifPresentielle,"passifPresential");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
    @GetMapping(value = "disable/{id}")
    public Object disable(@PathVariable Long id) {
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
            List<PassifPresentielle> items = commonInterface.getAll();
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
            PassifPresentielle item = commonInterface.getById(id);
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
            PassifPresentielle item = commonInterface.getById(id);
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
    @GetMapping("currentPassif/{valBci}")
    public Object getPassifPresentielleByvalReinitBCi(@PathVariable("valBci") Double valBci){
        try {
            PassifPresentielle item = commonInterface.getPassifPresentielleByvalReinitBCi(valBci);
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
    @GetMapping("code/{code}")
    public PassifPresentielle getPassifPresentielleByIdChiffrementQrCode(@PathVariable("code") String code){
          return  commonInterface.getPassifPrentielleByCodeQrOrNumRef(code);
    }
    @PostMapping(value = "selectPassifPresentielle")
    public Object selectPassifPresentielle(@RequestBody @Valid SelectPassifInput selectPassifInput ) {
        try {
            PassifPresentielle passifPresentielle =  commonInterface.selectPassifPresentielle(selectPassifInput);
            if( passifPresentielle ==null){
                return  renderStringData(false,"Error while procssing" ,"unable to execute this action. unsuficient found");
            }
            return  renderStringData(true, "element found"," done successfully");
        } catch (Exception e) {
           StringWriter sw = new StringWriter();
          e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }
    @GetMapping(value = "getSelectedPassifPresentielle/by/code/{code}")
    public Object getSelectedPassifPresentielle(@PathVariable("code") String code) {
        try {
            PassifPresentielle passifPresentielle =  commonInterface.getSelectedPassifPresentielle(code);
            if( passifPresentielle ==null){
                return  renderStringData(false,"Error while procssing" ,"unable to find the element" );
            }
            return  renderData(true, passifPresentielle,"find successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }
    @GetMapping(value = "getNumBon/{id}")
    public Object getCheckNumBon(@PathVariable("id") Long id) {
        try {
            String passifPresentielle =  commonInterface.checkView(id);
            if(passifPresentielle == null){
                return  renderStringData(false,"Error while processing" ,"unable to load information");
            }
            return  renderStringData(true, passifPresentielle,"Get Num Bon Command");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

}

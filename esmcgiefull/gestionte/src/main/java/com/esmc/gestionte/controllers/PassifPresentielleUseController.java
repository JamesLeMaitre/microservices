package com.esmc.gestionte.controllers;

import com.esmc.gestionte.entities.PassifPresentielle;
import com.esmc.gestionte.entities.PassifPresentielleHistory;
import com.esmc.gestionte.serviceinterface.PassifPresentielleInterface;
import com.esmc.input.PassifCheckInput;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import javax.validation.Valid;
import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@RequestMapping(value="/api/passifPresentielle/operation")
public class PassifPresentielleUseController extends DataFormatter<PassifPresentielleHistory> {
    @Autowired
    private PassifPresentielleInterface commonInterface;



    @GetMapping(value = "actionRequest/operation/{id}/type/{type}/numero/{numero}")
    public Object actionRequest(@PathVariable Long id, @PathVariable int type, @PathVariable String numero) {
        try {
            if( commonInterface.getById(id)==null){
                return  renderStringData(false,"Error while procssing" ,"item not found");
            }
            PassifPresentielleHistory passifPresentielleHistory = commonInterface.actionRequest(id,type,numero);
            if( passifPresentielleHistory ==null){
                return  renderStringData(false,"Error while procssing" ,"unable to execute this action. unsuficient found");
            }
            return  renderData(true, passifPresentielleHistory,"enable done successfully");
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
            return  renderStringData(true, commonInterface.checkPassifPrentielleUseByCodeQrOrNumRef(CodeQrOrNumRef, numero)+"","check by Numero Ref  done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,"Oppération échoué");
        }
    }



    @PostMapping(value = "checkExistPassifPresentielleByInfo")
    public Object checkExistPassifPresentielleByInfo(@RequestBody @Valid PassifCheckInput passifCheckInput ) {
       try {
           PassifPresentielle res =  commonInterface.checkExistPassifPresentielleByInfo(passifCheckInput);
        if( res ==null){
            return  renderStringData(false,"Error while procssing" ,"item not found");
        }
        Formatter<PassifPresentielle> formatter = new Formatter<>();
        formatter.setData(res);
        formatter.setStatus(true);
        formatter.setMessage("find and set to used successfully");

        return  formatter;
         } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }



}

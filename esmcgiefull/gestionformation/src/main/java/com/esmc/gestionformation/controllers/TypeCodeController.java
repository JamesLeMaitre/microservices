
package com.esmc.gestionformation.controllers;

import com.esmc.gestionformation.entities.TypeCodes;
import com.esmc.gestionformation.serviceinterfaces.TypeCodeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;


@RestController
@RequestMapping("api/TypeCodes/")
public class TypeCodeController extends DataFormatter<TypeCodes> {

    @Autowired
    private TypeCodeServiceInterface TypeCodesServiceInterface;

    @PostMapping("save")
    public Object addTypeCodes(@RequestBody TypeCodes t) {

        try {
            return renderData(true, TypeCodesServiceInterface.addTypeCodes(t), "Opperation Successiful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @PutMapping("update/{id}")
    public Object updateTypeCodes(@PathVariable Long id, @RequestBody TypeCodes t) {

        try {
            return renderData(true,  TypeCodesServiceInterface.updateTypeCodes(id, t), "Opperation Successiful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @DeleteMapping("delete/{id}")
    public void deleteTypeCodes(@PathVariable Long id) {
        TypeCodesServiceInterface.deleteTypeCodes(id);
    }

    @GetMapping("list")
    public Object listTypeCodes() {

        try {
            return renderDataArray(true, TypeCodesServiceInterface.listTypeCodes(), "Opperation Successiful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }
}

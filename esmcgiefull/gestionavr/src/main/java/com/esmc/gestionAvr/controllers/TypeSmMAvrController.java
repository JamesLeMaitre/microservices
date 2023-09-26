package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.DataSupport;
import com.esmc.gestionAvr.entities.TypeSmAvr;
import com.esmc.gestionAvr.servicesInterfaces.TypeSmAvrInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping(path = "api/typeSmMAvr/")
public class TypeSmMAvrController extends DataFormatter<TypeSmAvr> {

    @Autowired
    private TypeSmAvrInterface typeSmAvrInterface;

    @PostMapping("save")
    public TypeSmAvr saveTypeSmavr(@RequestBody TypeSmAvr t) {
        return typeSmAvrInterface.saveTypeSmavr(t);
    }

    @PutMapping("update/{id}")
    public TypeSmAvr updateTypeSmAvr(@RequestBody TypeSmAvr t, @PathVariable Long id) {
        t.setId(id);
        return typeSmAvrInterface.updateTypeSmAvr(t);
    }

    @DeleteMapping("delete/{id}")
    public void deleteTypeSmAvr(@PathVariable Long id) {
        typeSmAvrInterface.deleteTypeSmAvr(id);
    }

/*    @GetMapping("list")
    public List<TypeSmAvr> listTypeSmAvr() {
        return typeSmAvrInterface.listTypeSmAvr();
    }*/

    @GetMapping("list")
    public Object listTypeSmAvr() {
        try {
            List<TypeSmAvr> data = typeSmAvrInterface.listTypeSmAvr();

            return  renderDataArray(true, data,"Operation Successfully ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }

    /*@GetMapping("list/{id}/smavr")
    public List<TypeSmAvr> listTypeSmAvrBySMAvr(@PathVariable Long id) {
        return typeSmAvrInterface.listTypeSmAvrBySMAvr(id);
    }*/
}

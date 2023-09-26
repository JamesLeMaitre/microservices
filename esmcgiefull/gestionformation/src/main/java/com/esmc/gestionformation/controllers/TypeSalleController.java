package com.esmc.gestionformation.controllers;

import com.esmc.gestionformation.entities.TypeSalles;
import com.esmc.gestionformation.serviceinterfaces.TypeSalleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("api/type_salle/")
public class    TypeSalleController extends DataFormatter<TypeSalles> {


    @Autowired
    private TypeSalleServiceInterface TypeSallesServiceInterface;

    @PostMapping("save")
    public Object addTypeSalles(@RequestBody TypeSalles t) {

        try {
            return renderData(true, TypeSallesServiceInterface.addTypeSalles(t), "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PutMapping("update/{id}")
    public Object updateTypeSalles(@PathVariable Long id, @RequestBody TypeSalles t) {

        try {
            return renderData(true,  TypeSallesServiceInterface.updateTypeSalles(id, t), "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @DeleteMapping("delete/{id}")
    public void deleteTypeSalles(@PathVariable Long id) {
        TypeSallesServiceInterface.deleteTypeSalles(id);
    }

    @GetMapping("list")
    public Object listTypeSalles() {
        try {
            List<TypeSalles> items = TypeSallesServiceInterface.listTypeSalles();

            if (items.isEmpty()) {
                return renderStringData(false, "", "Data not found");
            }

            return renderDataArray(true, items, "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
}

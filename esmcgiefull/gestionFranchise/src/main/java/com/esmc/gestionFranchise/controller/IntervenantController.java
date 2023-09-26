package com.esmc.gestionFranchise.controller;

import com.esmc.gestionFranchise.entities.Intervenant;
import com.esmc.gestionFranchise.servicesInterface.IntervenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping(value = "api/Intervenant/")
public class IntervenantController extends DataFormatter<Intervenant> {
    @Autowired
    private IntervenantService intervenantService;

    @GetMapping("listall")
    public Object List() {
        try {
            List<Intervenant> items = intervenantService.getIntervenant();
            return renderDataArray(true, items, "list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @PostMapping("save")
    public Object create(@RequestBody() Intervenant data) {
        try {
            return renderData(true, intervenantService.ajouterIntervenant(data), "Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("listbytdep/{id}")
    public Object listByDept(@PathVariable("id") Long id){
        try {
            int count = intervenantService.countBy(id);
            List<Intervenant> items = intervenantService.getIntervenantbytdep(id);
            return renderDataArray(true, items, "("+ count + ") Element found");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("getBy/idPoste/{idPoste}/idTdep/{idTep}")
    public Object getByPosteTdep(@PathVariable("idPoste") Long idPoste, @PathVariable("idTep") Long idTep){
        try {
            Intervenant items = intervenantService.getByPosteTdep(idPoste,idTep);
            return renderData(true, items, "Element found");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @GetMapping("by/id/{id}")
    public Object getById(@PathVariable("id") Long id) {
        try {
            Intervenant item = intervenantService.getIntervenantbyId(id);
            if (item == null) {
                return renderStringData(false, "Error while processing", "item not found");
            }
            return renderData(true, item, "Element found");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }


    @DeleteMapping("delete/{id}")
    public Object delete(@PathVariable("id") Long id) {
        try {
            Intervenant item = intervenantService.getIntervenantbyId(id);
            if (item == null) {
                return renderStringData(false, "Error while processing", "item not found");
            }
            intervenantService.deleteIntervenant(id);
            return renderStringData(true, "Delete successfully", "Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }

    @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody Intervenant data) {
        try {
            if (intervenantService.getIntervenantbyId(id) == null) {
                return renderStringData(false, "Error while processing", "item not found");
            }
            return renderData(true, intervenantService.update(id, data), "update done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return renderStringData(false, "Error while processing", exceptionAsString);
        }
    }


}

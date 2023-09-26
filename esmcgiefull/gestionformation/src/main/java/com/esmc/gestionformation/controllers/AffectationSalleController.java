package com.esmc.gestionformation.controllers;

import com.esmc.gestionformation.entities.AffectationSalle;
import com.esmc.gestionformation.serviceinterfaces.SalleServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static constants.ControllerMessage.*;


@RestController
@RequestMapping("api/salles/")
public class AffectationSalleController extends DataFormatter<AffectationSalle> {

    @Autowired
    private SalleServiceInterface salleServiceInterface;

    @PostMapping("save")
    public Object addSalle(@RequestBody AffectationSalle s) {

        try {
            return renderData(true, salleServiceInterface.addSalle(s), "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PostMapping("saveFranchise")
    public Object addSalleFranchise(@RequestBody AffectationSalle s) {

        try {
            return renderData(true, salleServiceInterface.addSalle(s), "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PutMapping("update/{id}")
    public Object updateSalle(@PathVariable Long id, @RequestBody AffectationSalle s) {

        try {
            return renderData(true,  salleServiceInterface.updateSalle(id, s), "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @DeleteMapping("delete/{id}")
    public void deleteSalle(@PathVariable Long id) {
        salleServiceInterface.deleteSalle(id);
    }

    @GetMapping("list")
    public Object listSalle() {

//        try {
            return renderDataArray(true, salleServiceInterface.listSalle(), "Opperation Successiful");
//        } catch (Exception e) {
//            StringWriter sw = new StringWriter();
//            e.printStackTrace(new PrintWriter(sw));
//            String exceptionAsString = sw.toString();
//            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
//        }

    }

    @GetMapping("list/by/type/{idType}")
    public Object listSalleByType(@PathVariable Long idType) {

        try {
            return renderDataArray(true, salleServiceInterface.listSalleByType(idType), SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @GetMapping("getSalleByIdPoste/{idPoste}")
    public Object getSalleByIdPoste(@PathVariable Long idPoste) {

        try {
            List<AffectationSalle> affectionSalle = salleServiceInterface.getByIdPoste(idPoste);
            if(affectionSalle.isEmpty()){
                return renderStringData(false,NO_AFFECTATION,ERROR_MESSAGE);
            }
            return renderDataArray(true, affectionSalle, SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }



    @GetMapping("/{raisonsociale}")
    public Object getSalleBySalle(@PathVariable Long raisonsociale) {

        try {
            AffectationSalle s = salleServiceInterface.getSalleByRaisonSociale(raisonsociale);

            if(s == null) {
                return renderStringData(false, "", "Not found");
            }
            return renderData(true, s, "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("id/{id}")
    public Object getSalleById(@PathVariable Long id) {

        try {
            AffectationSalle s = salleServiceInterface.getSalleById(id);

            if(s == null) {
                return renderStringData(false, "", "Not found");
            }
            return renderData(true, s, "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


    /*
    Registe Path
     */


    @GetMapping("getFormationBy/idDetailsAgrFranchise/{idDetailsAgrFranchise}")
    public Object getListFormationByDetFranchise(@PathVariable("idDetailsAgrFranchise") Long id) {

        try {
            List<AffectationSalle> salles = salleServiceInterface.listByDetailsAgrFranchise(id);

            if(salles == null) {
                return renderStringData(false, "", "Not found");
            }
            return renderDataArray(true, salles, "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("getFormationBy/idKsu/{idKSU}")
    public Object getListFormationByKSU(@PathVariable("idKSU") Long id) {
        try {
            List<AffectationSalle> salles = salleServiceInterface.listFormationByKsu(id);
            if(salles == null) {
                return renderStringData(false, "", "Not found");
            }
            return renderDataArray(true, salles, "Operation Successful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


}

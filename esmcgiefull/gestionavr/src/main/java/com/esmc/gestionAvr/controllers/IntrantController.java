package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.Extrant;
import com.esmc.gestionAvr.entities.ExtrantIntrantResponse;
import com.esmc.gestionAvr.entities.Intrant;
import com.esmc.gestionAvr.feign.KsuClient;
import com.esmc.gestionAvr.servicesInterfaces.IntrantServiceInterface;
import com.esmc.models.Formatter;
import com.esmc.models.Ksu;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import javax.ws.rs.Path;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping(path = "api/intrants/")
public class IntrantController extends DataFormatter<Intrant> {

    @Autowired
    KsuClient ksuClient;
    @Autowired
    private IntrantServiceInterface intrantServiceInterface;

    /*
    * Function to save Intrant
    * call the route save
    * */
    @PostMapping(value = "ksu/{id}/save")
    public Intrant ajouterIntrant(@PathVariable Long id, @RequestBody Intrant a) {
        return intrantServiceInterface.addIntrant(id, a);
    }

    /*
     * Function to edit Intrant
     * call the route edit and add the id of the object
     * */

/*    @GetMapping(value = "edit/{id}")
    public ResponseEntity<Intrant> modifierIntrants(@PathVariable Long id, @RequestBody Intrant a) {
        return new ResponseEntity<Intrant>(intrantServiceInterface.updateIntrant(id, a), HttpStatus.OK) ;
    }*/

    @PutMapping("edit/{id}")
    public Object updateIntrant(@PathVariable Long id,@RequestBody Intrant a) {
        try {
            return  renderData(true, intrantServiceInterface.updateIntrant(id,a),"Update Successfully !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }




    /*
     * Function to delete Intrant
     * call the route delete and add the id of the object
     *
     * */

    @DeleteMapping(value = "delete/{id}")
    public void supprimerIntrants(@PathVariable Long id) {
        intrantServiceInterface.deleteIntrant(id);
    }

    /*
     * Function to get all Intrant
     * call the route list
     * */
    @GetMapping(value = "list")
    public Object listeIntrants(){
        try {
            List<Intrant> items = intrantServiceInterface.getAllIntrant();
            return  renderDataArray(true,items,"Element found");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    /*
     * Function to get all Intrant
     * call the route list
     * */
    @GetMapping(value = "getAllSupportEtablie/{id}")
    public Object getAllSupportEtablie(@PathVariable("id")Long id){
        try {
            List<Intrant> items = intrantServiceInterface.getAllSupportEtablie(id);
            return  renderDataArray(true,items,"La liste des supports dejà établie ! Merci :)");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    /*
     * Function to get all Intrant
     * call the route list
     * */
    @GetMapping(value = "archivage/{idPosteReceveur}")
    public Object archivage(@PathVariable("id") Long id){
        try {
            Intrant items = intrantServiceInterface.archivage(id);
            return  renderData(true,items,"Achivage effectuée avec succès ! Merci :) ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    /*
     * Function to get all Intrant
     * call the route list
     * */
    @GetMapping(value = "listArchivage/{idPosteReec}")
    public Object listArchivage(@PathVariable("id") Long id){
        try {
            List<Intrant> items = intrantServiceInterface.listArchivage(id);
            return  renderDataArray(true,items,"Liste des Intrants archivés! Merci :) ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    /*
     * Function to get intrant of a avr
     * call the route avr and add Id
     * */
//    @GetMapping(value = "list/{id}/avr")
//    public List<Intrant> recuperationIntrantAvr(@PathVariable Long id){
//        return intrantServiceInterface.recuperationIntrantAvr(id);
//    }

    @GetMapping(value = "list/{id}/avr")
    public Object recuperationIntrantAvr(@PathVariable("id") Long id){
        try {
            List<Intrant> items = intrantServiceInterface.recuperationIntrantAvr(id);
            return  renderDataArray(true,items,"Liste des Intrants by detAgr! Merci :) ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping(value = "list/{id}/ksu")
    public List<Intrant> IntrantByKsu(@PathVariable Long id) {
        return intrantServiceInterface.IntrantByKsu(id);
    }

    @GetMapping("list/by/avr/{id}")
    public Object ListByAvr(@PathVariable("id") Long id){
        try {

            List<Intrant> items = intrantServiceInterface.getByIdAvr(id);
            return  renderDataArray(true,items,"extrant by avr");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

    @GetMapping("list/by/detailagr/{id}")
    public Object ListByDetailAvr(@PathVariable("id") Long id){
        try {
            List<Intrant> items = intrantServiceInterface.getByIdDetailAgr(id);

            if (items.isEmpty()) {
                return renderStringData(false, "", "Data not found");
            }
            return  renderDataArray(true,items,"Opperation Successifully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }

    }

    @GetMapping("getTypeSupportByIdIntrant/idIntrant/{id}")
    public Object ListePosteReceveur(@PathVariable("id") Long id){
        try {
            String items = intrantServiceInterface.getByPosteReceveur(id);
            //return  renderStringData(true,items,"Libelle Intrant by posteEmetteur");
            if (items == null){
                return renderStringData(false,"", "Data not found");
            }
            return renderStringData(true, items, "Opperation successiful");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("listBy/posteReceveur/idPosteReceveur/{id}")
    public Object listeposteReceveur(@PathVariable("id") Long id){
        try {
            List<Intrant> items = intrantServiceInterface.getByListPosteReceveur(id);
            if (items.isEmpty()) {
                return renderStringData(false, "" , "Data not found");
            }
            return renderDataArray(true ,items, "Operation Successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


    @GetMapping(value = "getById/{id}")
    public Object IntrantWithKsu(@PathVariable("id") Long id){
        try {
            Intrant intrant = intrantServiceInterface.getExtrantById(id);
            if(intrant == null) {
                return  renderStringData(false," " ,"Data not found");
            }
            Ksu ksuRecepteur = ksuClient.getById(intrant.getKsuRecepteur());
            Ksu ksuEmetteur = ksuClient.getById(intrant.getKsuEmetteur());


            JSONObject jsonObjectEmetteur = new JSONObject();
            jsonObjectEmetteur.put("ksu",ksuEmetteur.getRaisonSocial()== null ?  ksuEmetteur.getNom() +" "+ksuEmetteur.getPrenom() : ksuEmetteur.getRaisonSocial());
            jsonObjectEmetteur.put("adresse",ksuRecepteur.getAdresse());
            jsonObjectEmetteur.put("signature",ksuRecepteur.getSignature());

            JSONObject  jsonObjectBon = new JSONObject();
            jsonObjectBon.put("refer",intrant.getRefer());
            jsonObjectBon.put("date",intrant.getDateCreate());
            jsonObjectBon.put("lieu","");
            jsonObjectBon.put("idBon",intrant.getId());
            jsonObjectBon.put("total",intrant.getMontant());
            jsonObjectBon.put("typeBon",intrant.getTypeSmAvr());
            jsonObjectBon.put("totalttc",intrant.getMontant());
            jsonObjectBon.put("totalht",intrant.getMontant());
            jsonObjectBon.put("tax",0);

            JSONObject [] jsonArrays = new JSONObject[1];
            JSONObject  jsonObjectItem = new JSONObject();
            jsonObjectItem.put("ordre",1);
            jsonObjectItem.put("reference",1);
            jsonObjectItem.put("article",intrant.getAvr().getLibelle());
            jsonObjectItem.put("prixUnitaire",intrant.getMontant());
            jsonObjectItem.put("unite","");
            jsonObjectItem.put("quantite",1);
            jsonObjectItem.put("valeur",intrant.getMontant());
            jsonArrays[0]= jsonObjectItem;



            JSONObject  jsonObjectRecepteur = new JSONObject();
            jsonObjectRecepteur.put("ksu",ksuRecepteur.getRaisonSocial()== null ?  ksuRecepteur.getNom() +" "+ksuRecepteur.getPrenom() : ksuRecepteur.getRaisonSocial());
            jsonObjectRecepteur.put("adresse",ksuRecepteur.getAdresse());
            jsonObjectRecepteur.put("signature",ksuRecepteur.getSignature());

            ExtrantIntrantResponse extrantIntrantResponse = new ExtrantIntrantResponse();
            extrantIntrantResponse.setEmetteur(jsonObjectEmetteur);
            extrantIntrantResponse.setRecepteur(jsonObjectRecepteur);
            extrantIntrantResponse.setBon(jsonObjectBon);
            extrantIntrantResponse.setItems(jsonArrays);


            Formatter<ExtrantIntrantResponse> formatter = new Formatter<>();
            formatter.setData(extrantIntrantResponse);
            formatter.setStatus(true);
            formatter.setMessage("intrant by id");
            return  formatter;
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("getIntrantByDetailsSupport/{id}")
    public Object getIntrantByDetailsSupport(@PathVariable("id") Long id){
        try {
            List<Intrant> items = intrantServiceInterface.getIntrantByDetailsSupport(id);
            return  renderDataArray(true,items,"List Intrant by Details support");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("listIntrantByDetailAgr/idDetailAgrRecepteur/{idDetailAgrRecepteur}")
    public Object listByDetailAgr(@PathVariable("idDetailAgrRecepteur") Long id){
        try {
            List<Intrant> items = intrantServiceInterface.getIntrantByDetailAgrFalse(id);
            return  renderDataArray(true,items,"List Intrant by Details support");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("listIntrantByDetailAgrArchiver/idDetailAgrRecepteur/{idDetailAgrRecepteur}")
    public Object listByDetailAgrArchiver(@PathVariable("idDetailAgrRecepteur") Long id){
        try {
            List<Intrant> items = intrantServiceInterface.getIntrantByDetailAgrTrue(id);
            return  renderDataArray(true,items,"List Intrant by DetailAgr archivées");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("listIntrantByDetailTypeSupport/idDetailTypeSupport/{idDetailTypeSupport}")
    public Object listByDetailTypeSupport(@PathVariable("idDetailTypeSupport") Long id){
        try {
            List<Intrant> items = intrantServiceInterface.getIntrantByDetailAgrTrue(id);
            return  renderDataArray(true,items,"List Intrant by DetailTypeSupport");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping(value = "archivage/idIntrant/{idIntrant}")
    public Object archivageByIdDetailAgr(@PathVariable("idIntrant") Long id){
        try {
            Intrant items = intrantServiceInterface.archivageByIdDetailAgr(id);
            return  renderData(true,items,"Achivage effectuée avec succès ! Merci :) ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
}

package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.DetailSupport;
import com.esmc.gestionAvr.entities.Extrant;
import com.esmc.gestionAvr.entities.ExtrantIntrantResponse;
import com.esmc.gestionAvr.feign.KsuClient;
import com.esmc.gestionAvr.inputs.ExtrantInput;
import com.esmc.gestionAvr.inputs.ExtrantInputv2;
import com.esmc.gestionAvr.servicesInterfaces.ExtrantServiceInterface;
import com.esmc.models.Formatter;
import com.esmc.models.Ksu;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping(path = "api/extrants/")
public class ExtrantController extends DataFormatter<Extrant> {

    private final KsuClient ksuClient;

    private final ExtrantServiceInterface extrantServiceInterface;

    public ExtrantController(KsuClient ksuClient, ExtrantServiceInterface extrantServiceInterface) {
        this.ksuClient = ksuClient;
        this.extrantServiceInterface = extrantServiceInterface;
    }
    /*
     * Function to save Extrant
     * call the route save
     * */

    @PostMapping(value = "add/support")
    public Extrant addExtrantSupport(@RequestBody ExtrantInput a) {
        return extrantServiceInterface.addExtrantSupport(a);
    }

    @PutMapping(value = "update/support/{id}")
    public DetailSupport updateExtrantSupport(@PathVariable Long id, @RequestBody ExtrantInput a) {
        return extrantServiceInterface.updateExtrantSupport(id,a);
    }

    @PostMapping(value = "ksu/{idksu}/save")
    public Extrant ajouterExtrant(@PathVariable Long idksu, @RequestBody Extrant a) {
        return extrantServiceInterface.addExtrant(idksu, a);
    }

    /*
     * Function to update Extrant
     * call the route edit and add the id of the object
     *
       @GetMapping(value = "edit/{id}")
    public ResponseEntity<Extrant> modifierExtrants(@PathVariable Long id, @RequestBody Extrant a) {
        return new ResponseEntity<Extrant>(extrantServiceInterface.updateExtrant(id, a), HttpStatus.OK);
    }*/


    @PutMapping("edit/{id}")
    public Object updateExtrants(@PathVariable Long id,@RequestBody Extrant a) {
        try {
            return  renderData(true, extrantServiceInterface.updateExtrant(id,a),"Update Successfully !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    /*
     * Function to delete Extrant
     * call the route delete and add the id of the object
     *
    @DeleteMapping(value = "delete/{id}")
    public void supprimerExtrants(@PathVariable Long id) {
        extrantServiceInterface.deleteExtrant(id);
    }*/
    @DeleteMapping("delete/{id}")
    public Object supprimerExtrants(@PathVariable Long id) {
        try {
            extrantServiceInterface.deleteExtrant(id);
            return  renderStringData(true,"Is zoooo" ,"Delete Successfully !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @DeleteMapping("deletedev/{id}")
    public Object del(@PathVariable Long id) {
        try {
            extrantServiceInterface.deleteDev(id);
            return  renderStringData(true,"Is zoooo v2" ,"Delete Successfully !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @DeleteMapping("vider-extrant/id-trants{id}")
    public Object deletExt(@PathVariable Long id) {
        try {
            extrantServiceInterface.deleteDev(id);
            return  renderStringData(true,"Is zoooo v2" ,"Delete Successfully !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


    /*
     * Function to get all Extrant
     * call the route list
     * */
    @GetMapping(value = "list")
    public Object listeExtrants(){
        try {
            List<Extrant> items = extrantServiceInterface.getAllExtrant();
            return  renderDataArray(true,items,"Element found");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    /*
     * Function to get all Extrant
     * call the route list
     * */
    @GetMapping(value = "listArchivage/{idPosteEmetteur}")
    public Object listArchivage(@PathVariable("idPosteEmetteur") Long id){
        try {
            List<Extrant> items = extrantServiceInterface.listArchivage(id);
            return  renderDataArray(true,items,"Les extrants archivés sont là ! Remercie TCHAO pour le travail abattu Merci:)");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping(value = "archivage/{id}")
    public Object archivage(@PathVariable("id") Long id){
        try {
            Extrant items = extrantServiceInterface.archivage(id);
            return  renderData(true,items,"Achivage effectué avec succès! Thanks :)");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping(value = "viderExtrant/idExtrant/{idExtrant}")
        public Object viderExt(@PathVariable("idExtrant") Long id){
        try {
            Extrant items = extrantServiceInterface.viderExtrant(id);
            if(items==null){
                return  renderStringData(true,"","Not Done");
            }
            return  renderData(true,items,"Extrant vidé avec succès! Thanks :)");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


    /*
     * Function to get extrants of an avr
     * call the route avr and add Id
     * */
    @GetMapping(value = "list/{id}/avr")
    public List<Extrant> recuperatiionExtrantAvr(@PathVariable Long id){
        return extrantServiceInterface.recuperatiionExtrantAvr(id);
    }

    @GetMapping("getExInTrantsById/{id}")
    public Extrant getByIdExIn(@PathVariable Long id){
        return extrantServiceInterface.getByID(id);
    }

    /* @GetMapping(value = "list/{id}/ksu")
    public List<Extrant> ExtrantByKsu(@PathVariable Long id) {
        return extrantServiceInterface.ExtrantByKsu(id);
    }*/

    @GetMapping(value = "getById/{id}")
    public Object ExtrantWithKsu(@PathVariable("id") Long id){
        try {
            Extrant extrant = extrantServiceInterface.getExtrantById(id);
            if(extrant == null) {
                return  renderStringData(false,"Error while processing" ,"item not found");
            }

            Ksu ksuRecepteur = ksuClient.getById(extrant.getKsuRecepteur());
            Ksu ksuEmetteur = ksuClient.getById(extrant.getKsuEmetteur());

            JSONObject jsonObjectEmetteur = new JSONObject();
            jsonObjectEmetteur.put("ksu",ksuEmetteur.getRaisonSocial()== null ?  ksuEmetteur.getNom() +" "+ksuEmetteur.getPrenom() : ksuEmetteur.getRaisonSocial());
            jsonObjectEmetteur.put("adresse",ksuRecepteur.getAdresse());
            jsonObjectEmetteur.put("signature",ksuRecepteur.getSignature());

            JSONObject  jsonObjectBon = new JSONObject();
            jsonObjectBon.put("refer",extrant.getRefer());
            jsonObjectBon.put("date",extrant.getDateCreate());
            jsonObjectBon.put("lieu","");
            jsonObjectBon.put("idBon",extrant.getId());
            jsonObjectBon.put("totalttc",extrant.getMontant());
            jsonObjectBon.put("totalht",extrant.getMontant());
            jsonObjectBon.put("tax",0);
            jsonObjectBon.put("typeBon",extrant.getTypeSmAvr());

            JSONObject [] jsonArrays = new JSONObject[1];
            JSONObject  jsonObjectItem = new JSONObject();
            jsonObjectItem.put("ordre",1);
            jsonObjectItem.put("reference",1);
            jsonObjectItem.put("article",extrant.getAvr().getLibelle());
            jsonObjectItem.put("prixUnitaire",extrant.getMontant());
            jsonObjectItem.put("unite","");
            jsonObjectItem.put("quantite",1);
            jsonObjectItem.put("valeur",extrant.getMontant());
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


            Formatter<ExtrantIntrantResponse>  formatter = new Formatter<>();
            formatter.setData(extrantIntrantResponse);
            formatter.setStatus(true);
            formatter.setMessage("extrant by id");
            return  formatter;
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }



    @GetMapping(value = "list/{id}/ksu")
    public Object ExtrantByKsu(@PathVariable("id") Long id){
        try {
            List<Extrant> items =  extrantServiceInterface.ExtrantByKsu(id);
            return  renderDataArray(true,items,"extrant by detail agr");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("list/by/avr/{id}")
    public Object ListByAvr(@PathVariable("id") Long id){
        try {

            List<Extrant> items = extrantServiceInterface.getByIdAvr(id);
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

            List<Extrant> items = extrantServiceInterface.getByIdDetailAgr(id);
            return  renderDataArray(true,items,"extrant by detail agr");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


    @GetMapping("list/by/posteEmetteur/{id}")
    public Object ListByPosteEmetteur(@PathVariable("id") Long id){
        try {
            String items = extrantServiceInterface.getByIdPosteEmetteur(id);
            if(items == null){
                return renderStringData(false, "Error getting Type Support ID","Error while processing");
            }
           return items;
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("getTypeSupportByIdExtrant/idExtrant/{id}")
    public Object libelle(@PathVariable("id") Long id){
        try {
            String items = extrantServiceInterface.getLibelle(id);
            if(items == null){
                return  renderStringData(false,"Error Getting  TypeSupport ID","Error while processing");
            }
            return  renderStringData(true,items,"Libelle TypeSupport by Poste Emetteur");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
    /*
    *  @return Liste des extrants en fonction de l'id posteMem
     */
    @GetMapping("listBy/posteEmetteur/idPosteEmetteur/{id}")
    public Object getListByPosteEmetteur(@PathVariable("id") Long id){
       try {
            List<Extrant> items = extrantServiceInterface.getByListPosteEmetteur(id);
           return  renderDataArray(true,items,"List Extrant by Poste Emetteur");
       } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
         }
    }

    @GetMapping("listByEtablie/posteEmetteur/idPosteEmetteur/{id}")
    public Object getListByPosteEmetteurEtablie(@PathVariable("id") Long id){
        try {
            List<Extrant> items = extrantServiceInterface.getListByPosteEmetteurEtablie(id);
            return  renderDataArray(true,items,"List Extrant by Poste Emetteur");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("getExtrantByDetailsSupport/{id}")
    public Object getExtrantByDetailsSupport(@PathVariable("id") Long id){
        try {
            List<Extrant> items = extrantServiceInterface.getExtrantByDetailsSupport(id);
            return  renderDataArray(true,items,"List Extrant by Details support");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

//    @PostMapping("add/supportv2")
    public Object create(@RequestBody() ExtrantInputv2 data){
      try {
            extrantServiceInterface.addExtrantSupportv2(data);
         //return "ok";
            return  renderStringData(true, "Your extrant has been sent successfully ! Thanks","Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    /// Extrant by DetailAgr

    @GetMapping("listExtrantByDetailsAgr/idDetailsAgrEmetteur/{idDetailsAgrEmetteur}")
    public Object listByDetailAgr(@PathVariable("idDetailsAgrEmetteur") Long id){
        try {
            List<Extrant> items = extrantServiceInterface.getExtrantByDetailAgrFalse(id);
            return  renderDataArray(true,items,"List Extrant by Details support");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("listExtrantByDetailsAgrArchiver/idDetailsAgrEmetteur/{idDetailsAgrEmetteur}")
    public Object listByDetailAgrArchiver(@PathVariable("idDetailsAgrEmetteur") Long id){
        try {
            List<Extrant> items = extrantServiceInterface.getExtrantByDetailAgrTrue(id);
            int count = extrantServiceInterface.countBy(id);
            return  renderDataArray(true,items,count+ " List Extrant by DetailAgr archivées");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }
    @GetMapping(value = "archivage/idExtrant/{idExtrant}")
    public Object archivageByIdDetailAgr(@PathVariable("idExtrant") Long id){
        try {
            Extrant items = extrantServiceInterface.archivageByIdDetailAgr(id);
            return  renderData(true,items,"Achivage effectuée avec succès ! Merci :) ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    // ======================
    @PostMapping("add/support-custom")
    public Object createv2(@RequestBody() ExtrantInputv2 data){
        try {
            extrantServiceInterface.addExtrantSupportv3(data);
            //return "ok";
            return  renderStringData(true, "Your extrant has been sent successfully ! Thanks","Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

}

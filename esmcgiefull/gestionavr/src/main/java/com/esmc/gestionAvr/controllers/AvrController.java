package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.InterimFeign.TeInterimClient;
import com.esmc.gestionAvr.constant.AvrRequest;
import com.esmc.gestionAvr.entities.Avr;
import com.esmc.gestionAvr.feign.AchatKsuRestClient;
import com.esmc.gestionAvr.feign.DetailAgrClient;
import com.esmc.gestionAvr.feign.KsuClient;
import com.esmc.gestionAvr.inputs.AvrInput;
import com.esmc.gestionAvr.servicesInterfaces.AvrServiceInterface;
import com.esmc.models.BanKsu;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Ksu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping(path = "api/avrs/")
public class AvrController extends DataFormatter<Avr> {

    @Autowired
    private AvrServiceInterface avrServiceinterface;

    @Autowired
    TeInterimClient teInterimClient;

    @Autowired
    private DetailAgrClient detailAgrClient;

    @Autowired
    private KsuClient ksuClient;

    @Autowired
    private  AchatKsuRestClient achatKsuRestClient;



    /*@PostMapping(value = "detailAgr/{id}/save")
    public Avr ajouterAvr(@PathVariable Long id, @ModelAttribute @Valid AvrRequest a1) throws Exception {
        return avrServiceinterface.addAvr(id, a1);
    }*/

    @GetMapping("ksu/{id}")
    public Ksu getVendeur(@PathVariable Long id) {
        return avrServiceinterface.getVendeur(id);
    }

    @PostMapping("saveFifoAvr/{id}/detailAgr")
    public ResponseEntity<Avr> ajoutAchat(@PathVariable Long id, @RequestBody AvrRequest a) throws Exception {
        return new ResponseEntity<Avr> (avrServiceinterface.addAvrV2s(a, id), HttpStatus.OK);
    }

    @PostMapping(value = "detailAgr/{id}/save")
    public Object ajouterAvr(@PathVariable Long id, @RequestBody AvrRequest a1) throws Exception {

        try {
            Avr avr = avrServiceinterface.addAvrV2s(a1,id);
            if(avr == null){
               return  renderStringData(false, "","Vous ne disposez pas de resource suffisante pour éffectuer cette Opération");
            }
            return  renderData(true, avr,"Opération éffectuer avec succès !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PostMapping(value = "detailAgr/{id}/saveAchat")
    public Object ajouterAvrAchat(@PathVariable Long id, @RequestBody AvrRequest a1) throws Exception {

        try {
            Avr avr = avrServiceinterface.addAvrV2sAchat(a1,id);
            if(avr == null){
                return  renderStringData(false, "","Vous ne disposez pas de resource suffisante pour éffectuer cette Opération");
            }
            return  renderData(true, avr,"Opération éffectuer avec succès !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PostMapping(value = "detailAgr/{id}/saveVente")
    public Object ajouterAvrVente(@PathVariable Long id, @RequestBody AvrRequest a1) throws Exception {

        try {
            Avr avr = avrServiceinterface.addAvrV2sVente(a1,id);
            if(avr == null){
                return  renderStringData(false, "","Vous ne disposez pas de resource suffisante pour éffectuer cette Opération");
            }
            return  renderData(true, avr,"Opération éffectuer avec succès !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,"Opération échouée !");
        }
    }

    @PutMapping(value = "edit/{id}")
    public Avr modifierAvr(@PathVariable Long id, @RequestBody Avr avr) {
        avr.setId(id);
        return avrServiceinterface.updateAvr(avr);
    }

    @DeleteMapping(value = "delete/{id}")
    public void supprimerAvr(@PathVariable Long id) throws Exception {
        avrServiceinterface.deleteAvr(id);
    }

    @GetMapping(value = "list")
    public List<Avr> listeAvr() {
        return avrServiceinterface.listAvr();
    }

    @GetMapping(value = "get/{id}")
    public Avr getAvrById(@PathVariable Long id) {
        return avrServiceinterface.getAvrById(id);
    }

    /*
     * Function to get Avr of a detailsAgr
     * call the route agr and add Id of detailsAgr
     * */
    @GetMapping(value = "list/{id}/detailsagr")
    public List<Avr> listAvrByDetailAgrId(@PathVariable Long id) {
        return avrServiceinterface.listAvrByDetailAgrId(id);
    }

    /*
     * Function to get Avr of a detailsAgr by type
     * call the route agr and add Id of detailsAgr and type of typeAvr
     * */
    @GetMapping(value = "list/{id}/detailsagr/{type}/typeavr")
    public List<Avr> listAvrByDetailAgrIdAndType(@PathVariable Long id, @PathVariable Long type) {
        return avrServiceinterface.listAvrByDetailAgrIdAndType(id, type);
    }


    @GetMapping(value = "list/{id1}/categorie/{id2}/typeavr/{id3}/detail_agr")
    public List<Avr> listAvrByCategorieAvrIdAndType(@PathVariable Long id1, @PathVariable Long id2, @PathVariable Long id3) {
        return avrServiceinterface.listAvrByCategorieAvrIdAndType(id1, id2, id3);
    }

    /**
     * Function to get Avr by type
     *
     * @param id
     * @return
     */
    @GetMapping(value = "list/{id}/typerav")
    public List<Avr> listAvrByType(@PathVariable Long id) {
        return avrServiceinterface.listAvrByType(id);
    }

    /**
     * Function to get Avr by categorie
     *
     * @param id
     * @return
     */
    @GetMapping(value = "list/{id}/categorieavr")
    public List<Avr> listAvrByCategorie(@PathVariable Long id) {
        return avrServiceinterface.listAvrByCategorie(id);
    }


    /*
     * Function to get Avr of a detailsAgr by type
     * call the route agr and add Id of detailsAgr and type of typeAvr
     * libelle = nom
     * type = achat ou vente
     * categorie = categorie d'avr
     * */
    @GetMapping(value = "list/{libelle}/libelle/{type}/typeavr/{categorie}/categorieavr")
    public List<Avr> listBesoinsAvr(@PathVariable String libelle, @PathVariable Long type, @PathVariable Long categorie) {
        return avrServiceinterface.listBesoinsAvr(libelle, type, categorie);
    }

    /**
     *
     * @param
     * @return
     @GetMapping(value = "list/{libelle}/")
     public List<Avr> listAvrByLibelle(@PathVariable String libelle){
     return  avrServiceinterface.listAvrByLibelle(libelle);
     }*/

    @PostMapping("simmple/saveFifoAvr/{id}/detailAgr")
    public Object ajouSimpletAchat(@PathVariable Long id, @RequestBody AvrRequest a) throws Exception {
        try {
            Avr avr = avrServiceinterface.addAvrAchatSimple(a, id);
            if(avr == null){
                return  renderStringData(false, "","Unable to operate due to somes issues .Please contact the cloud service ");
            }
            return  renderData(true, avr,"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

  @GetMapping("createSupportMarchandAvr/detailAgrVente/{idDetailAgrVente}/quantity/{quantite}/detailAgrAcheteur/{idDetailAgrAcheteur}/montant/{montant}/produit/{produit}")
    public Boolean createSupportMarchandAvr(@PathVariable("idDetailAgrVente") Long idDetailAgrVente,
                                            @PathVariable("quantite")  int quantite,
                                            @PathVariable("idDetailAgrAcheteur")  Long idDetailAgrAchat,
                                            @PathVariable("montant")  Double montant,
                                            @PathVariable("produit")  String produit){
        try {
            avrServiceinterface.CreateBonSmvAvrProcess(idDetailAgrVente,quantite,idDetailAgrAchat, montant,produit);
            return  true;
       } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return false;
        }

    }

    @GetMapping("createSupportMarchandAvrV2/detailAgrVente/{idDetailAgrVente}/quantity/{quantite}/detailAgrAcheteur/{idDetailAgrAcheteur}/montant/{montant}/produit/{produit}")
    public Boolean createSupportMarchandAvrV2(@PathVariable("idDetailAgrVente") Long idDetailAgrVente,
                                            @PathVariable("quantite")  int quantite,
                                            @PathVariable("idDetailAgrAcheteur")  Long idDetailAgrAchat,
                                            @PathVariable("montant")  Double montant,
                                            @PathVariable("produit")  String produit){
        try {
            avrServiceinterface.CreateBonSmvAvrProcessV2(idDetailAgrVente,quantite,idDetailAgrAchat, montant,produit);
            return  true;

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();

            return false;
        }

    }

    @GetMapping("createSupportMarchandAvrV4/detailAgrVente/{idDetailAgrVente}/posteRecepteur/{posteRecepteur}/quantity/{quantite}/detailAgrAcheteur/{idDetailAgrAcheteur}/montant/{montant}/produit/{produit}")
    public Boolean createSupportMarchandAvrV3(@PathVariable("idDetailAgrVente") Long idDetailAgrVente,
                                              @PathVariable("posteRecepteur")  Long posteRecepteur,
                                              @PathVariable("quantite")  int quantite,
                                              @PathVariable("idDetailAgrAcheteur")  Long idDetailAgrAchat,
                                              @PathVariable("montant")  Double montant,
                                              @PathVariable("produit")  String produit){

        try {
            avrServiceinterface.CreateBonSmvAvrProcessV3(idDetailAgrVente,posteRecepteur, quantite,idDetailAgrAchat, montant,produit);
            return  true;

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();

            return false;
        }

    }

    @PostMapping("createSupportMarchandAvrv3")
    public Object createSupportMarchandAvrv3(@RequestBody AvrInput a) throws Exception {
        try {

            BanKsu b = achatKsuRestClient.getBanKsuByCodeBan(a.getCodeBan());

            Double montant = (double) b.getTotal();

            System.out.println("============================================================");
            System.out.println("Montant : "+montant);
            System.out.println("============================================================");

            Ksu k = ksuClient.getById(a.getIdDetailAgrAcheteur());

            System.out.println("============================================================");
            System.out.println(k);
            System.out.println("============================================================");

            List<DetailsAgr> listagrs = detailAgrClient.listDetailAgrParKsu(k.getId());

            for (DetailsAgr d: listagrs) {
                if (d.getTypeMaBanKsuAgr().getAgr().getLibelle().equals("OP")) {
                    System.out.println("============================================================");
                    System.out.println(d);
                    System.out.println("============================================================");
                    avrServiceinterface.CreateBonSmvAvrProcessV2(a.getIdDetailAgrVente(),a.getQuantite(),d.getId(), montant,a.getProduit());
                }
            }

            return renderStringData(true, "", "Opération éffecruer avec Succès");

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();

            return renderStringData(false, "", "Error while proccessing");
        }
    }

    @PostMapping("createSupportMarchandAvr/detailAgrVente/quantity/detailAgrAcheteur/montant/produit")
    public Object createSupportMarchandAvrv2(@RequestBody AvrRequest a) throws Exception {
        try {

            Avr avr = avrServiceinterface.addAvrAchatSimple(a, a.getIdDetailAgrVente());
            if(avr == null){
                return  renderStringData(false, "","Unable to operate due to somes issues .Please contact the cloud service ");
            }
            return  renderData(true, avr,"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("produit_fifo/idKsu/{idKsu}")
    public Object getProduct(@PathVariable("idKsu") Long idKsu) {
        try {
            String item = avrServiceinterface.listAvrProduit(idKsu);
            if (item.isEmpty()) {
                return renderStringData(false, "", "Il n'y a pas de donnée");
            }
            return renderStringData(true, item, "Opération éffectué avec succès");
        } catch (Exception e) {
            return renderStringData(false, "", "Erreur au niveau du serveur");
        }
    }


}

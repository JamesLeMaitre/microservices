package com.esmc.gestionMembre.services.recherche;

import com.esmc.gestionMembre.entities.Releve;
import com.esmc.gestionMembre.entities.ReleveInput;
import com.esmc.gestionMembre.serviceInterfaces.recherche.ReleveServiceInterface;
import com.esmc.gestionMembre.services.RedemareService;
import com.esmc.models.DetailSMEnchange;
import com.esmc.models.Ksu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import java.util.List;
@RestController
@RequestMapping("api/releves/")
public class ReleveController extends DataFormatter<Object> {

    @Autowired
    private ReleveServiceInterface releveServiceInterface;

    @Autowired
    private RedemareService redemareService;


    @GetMapping("{systeme}/{produit}/{code}/{idKsu}/te/{idTe}")
    public Object ajouterRelever(@PathVariable String systeme, @PathVariable String produit, @PathVariable String code, @PathVariable Long idKsu, @PathVariable Long idTe) {

        Object item =  releveServiceInterface.saveReleve(systeme, produit, code, idKsu, idTe);

        if (item == null) {
            return renderStringData(false, "", "Vous n'êtes pas autorisé à faire cette demande de relevés ");
        }

        return renderData(true, item, "Oppreration Succefully");
    }

    @GetMapping("ksu/{idKsu}/te/{idTe}/{produit}")
    public List<Releve> getReleve(@PathVariable Long idKsu, @PathVariable Long idTe, @PathVariable String produit) {
        return releveServiceInterface.listReleveByKsuAndProduit(idKsu, idTe, produit);
    }

    @GetMapping("valider/{systeme}/{produit}/{code}/{idKsu}/te/{idTe}")
    public boolean validerReleve(@PathVariable("systeme") String systeme, @PathVariable("produit") String produit, @PathVariable("code") String code, @PathVariable Long idKsu, @PathVariable Long idTe) {
        return releveServiceInterface.validationReleve(systeme, produit, code, idKsu, idTe);
    }

    @GetMapping("listProduit/{id}/te/{idTe}")
    public List<String> listReleveByProduit(@PathVariable Long id, @PathVariable Long idTe) {
        return releveServiceInterface.listReleveByProduit(id, idTe);
    }

    /**
     * @param a
     * @return
     */
    @PostMapping(value = "save")
    public Releve saveReleve(@RequestBody Releve a) {
        return releveServiceInterface.saveReleve(a);
    }

    /**
     * @return
     */
    @GetMapping(value = "listall")
    public List<Releve> listeDesreleves() {
        return releveServiceInterface.listeDesreleves();
    }

    /**
     * @param a
     * @return
     */
    @GetMapping(value = "list/{ksu}")
    public List<Releve> listeReleveKsu(@PathVariable Ksu a) {
        return releveServiceInterface.listeReleveKsu(a);
    }

    /**
     * @param a
     * @return
     */
    @GetMapping(value = "get/{ksu}")
    public Releve releveDeKsu(@PathVariable Ksu a) {
        return releveServiceInterface.releveDeKsu(a);
    }

    /**
     * @param a
     * @return
     */
    @PutMapping("validerRelever")
    public Releve validerReleve(Releve a) {
        return releveServiceInterface.validerReleve(a);
    }

    /**
     * @param a
     * @return
     */
    @PutMapping("cloturerRelever")
    public Releve cloturerReleve(Releve a) {
        return releveServiceInterface.cloturerReleve(a);
    }

    @GetMapping("renitialisationBCI/montant/{montant}/idRe/{idRe}/idTe/{idTe}")
    public Object listeReleveKsu(@PathVariable("montant") Double montant, @PathVariable Long idRe, @PathVariable("idTe") Long idTe) {

        try {
            DetailSMEnchange d = releveServiceInterface.renitialisationBCI(montant, idTe);

            if (d == null) {
                return null;
            }
            Releve r = releveServiceInterface.getReleveByIdAndMontant(idRe, montant);
            r.setCloture(true);
            releveServiceInterface.saveReleve(r);
            return  d;
        } catch (Exception e) {
            return renderStringData(false, "", "Erreur au niveau du serveur");
        }


    }

    @GetMapping("renitialisationPassif/montant/{montant}/idRe/{idRe}/idTe/{idTe}")
    public DetailSMEnchange releveKsuPassif(@PathVariable("montant") Double montant, @PathVariable("idRe") Long idRe, @PathVariable("idTe") Long idTe) {

        DetailSMEnchange d = releveServiceInterface.renitialisationPassif(montant, idTe);

        if (d == null) {
            return null;
        }

        Releve r = releveServiceInterface.getReleveByIdAndMontant(idRe, montant);
        r.setCloture(true);
        releveServiceInterface.saveReleve(r);

        return d;
    }

    @PostMapping("renitialisationPassifsPresentiel")
    public DetailSMEnchange releveKsuPassifsPresentiel(@RequestBody() ReleveInput releveInput) {

        DetailSMEnchange d = null;
        Long[] idReleves = releveInput.getIdRe();

        for (Long idRel : idReleves) {

            Releve releve = releveServiceInterface.getReleveById(idRel);

            d = releveServiceInterface.renitialisationBCI(releve.getMontant(), releve.getTerminalEchange());

            if (d == null) {
                return null;
            }

            Releve r = releveServiceInterface.getReleveByIdAndMontant(idRel, releve.getMontant());
            r.setCloture(true);
            releveServiceInterface.saveReleve(r);

        }

        return d;

    }

    @PostMapping("renitialisationPassifs")
    public DetailSMEnchange releveKsuPassifs(@RequestBody() ReleveInput releveInput) {

        DetailSMEnchange d = null;
        Long[] idReleves = releveInput.getIdRe();

       for (Long idRel : idReleves) {

          Releve releve = releveServiceInterface.getReleveById(idRel);

           d = releveServiceInterface.renitialisationPassif(releve.getMontant(), releve.getTerminalEchange());

           if (d == null) {
               return null;
           }

           Releve r = releveServiceInterface.getReleveByIdAndMontant(idRel, releve.getMontant());
           r.setCloture(true);
           releveServiceInterface.saveReleve(r);

       }

        return d;

    }

    @GetMapping("code_membre_red/{code}")
   public String codeMembre(@PathVariable("code") String code) {
        return releveServiceInterface.codeMembreRedemare(code);
    }

    @GetMapping("code_membre_mcnp/{code}")
    public String codeMembreMCNP(@PathVariable("code") String code) {
        return releveServiceInterface.codeMembre(code);
    }

    @GetMapping("code_membre_gie/{code}")
    public String codeMembreGie(@PathVariable("code") String code) {
        return releveServiceInterface.codeMembreGie(code);
    }

    @GetMapping("id/{id}")
    public Releve getReleveById(@PathVariable("id") Long id) {
        return releveServiceInterface.getReleveById(id);
    }

}

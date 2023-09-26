package com.esmc.gestionMembre.serviceInterfaces.recherche;

import com.esmc.gestionMembre.entities.Credit;
import com.esmc.gestionMembre.entities.Releve;
import com.esmc.models.DetailSMEnchange;
import com.esmc.models.Ksu;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ReleveServiceInterface {
    Releve saveReleve(Releve a);
    List<Releve> listeDesreleves();
    List<Releve>listeReleveKsu(Ksu a);
    Releve releveDeKsu(Ksu a);
    Releve validerReleve(Releve a);
    Releve cloturerReleve(Releve a);

    public Object saveReleve(String systeme, String produit, String code, Long idKsu, Long idTe);

    public List<Releve> listRevele(Long idKsu, Long idTe);

    public boolean validationReleve(String systeme, String produit,  String code,  Long idKsu, Long idTe);

    public List<String> listReleveByProduit(Long id, Long idTe);

    public List<Releve> listReleveByKsuAndProduit(Long id, Long idTe, String produit);

   public DetailSMEnchange renitialisationBCI(Double montant, Long idTe);

    DetailSMEnchange renitialisationPassif(Double montant, Long idTe);

    public Releve getReleveByIdAndMontant(Long id, double valeur);

    String codeMembreRedemare(String code);

    String codeMembre(String code);

    String codeMembreGie(String code);

    Releve getReleveById(Long id);
}

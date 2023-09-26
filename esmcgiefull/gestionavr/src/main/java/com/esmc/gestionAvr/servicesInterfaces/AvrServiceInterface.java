package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.constant.AvrRequest;
import com.esmc.gestionAvr.entities.Avr;
import com.esmc.models.Ksu;

import java.util.List;

public interface AvrServiceInterface {

    public Avr addAvr (AvrRequest a1,Long id) throws Exception;

    public Avr addAvrs(AvrRequest a, Long id) throws Exception;
    public Avr updateAvr( Avr avr);
    public void deleteAvr(Long id) throws Exception;
    public List<Avr> listAvr();

    public Avr getAvrById (Long id);
    public List<Avr> listAvrByDetailAgrId(Long id);
    public List<Avr> listAvrByDetailAgrIdAndType(Long id, Long type);
    public List<Avr> listBesoinsAvr(String libelle,Long type, Long categorie);
    public List<Avr> listAvrByType(Long id);

    public List<Avr> listAvrByCategorieAvrIdAndType(Long id1, Long id2, Long id3);
    public Avr getAvrByKsu (Long id);

    public List<Avr> listAvrByCategorie (Long id);
    /*List<Avr> listAvrByLibelle(String libelle);*/

    public Ksu getVendeur(Long id);


    Avr saveAvr(Avr avr);

    Double amountBciAvailable(Long idKsu);

    Avr addAvrV2s(AvrRequest a1, Long id) throws Exception;
    Avr addAvrV2sVente(AvrRequest a1, Long id) throws Exception;

    Avr addAvrV2sAchat(AvrRequest a1, Long id) throws Exception;

    Avr addAvrAchatSimple(AvrRequest a1, Long id) throws Exception;

    void CreateBonSmvAvrProcess(Long idDetailAgrVente, int quantite, Long idDetailAgrAchat, Double montant, String produit);

    void CreateBonSmvAvrProcessV3(Long idDetailAgrVente, Long posteRecepteur, int quantite, Long idDetailAgrAchat, Double montant, String produit);

    void CreateBonSmvAvrProcessV2(Long idDetailAgrVente, int quantite, Long idDetailAgrAchat, Double montant, String produit);

    Avr findAvrById(Long id);

    String listAvrProduit(Long idPro);
}

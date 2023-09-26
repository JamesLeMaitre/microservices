package com.esmc.gestionte.serviceinterface;

import com.esmc.gestionte.entities.Promotion;
import com.esmc.gestionte.exceptions.Exceptions;


import java.util.Date;
import java.util.List;

public interface PromotionInterface {

    /**
     * Liste de tout les ordre
     * @return
     */
    List<Promotion> getPromotion();

    /**
     * creation d'un promotion
     * @param promotion
     */
    public void addNewPromotion(Promotion promotion) throws Exceptions;

    /**
     * supprimer une promotion
     * @param id
     */
    void deletePromotion(Long id) throws Exceptions;

    /**
     * modifiacation d'une promotion
     * @param id
     * @param promotion
     * @throws Exceptions
     */

    public void updatePromotion(Long id, Promotion promotion)throws Exceptions;

    /**
     *  recherche par id
     * @param id
     * @return
     * @throws Exceptions
     */

    public Promotion getById(Long id) throws  Exceptions;

    public Double montantPromotion(Date debut, Date defin, double montantBci);

    public Promotion getPromotionByDateDebutAndDateFin(Date dateDebut, Date dateFin);

    public Promotion getPromotionTrue();

    Double montantPromotionBCI(Date debut, Date defin, double montantBan);
}

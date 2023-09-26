package com.esmc.gestionte.serviceinterface;


import com.esmc.gestionte.entities.Ordre;
import com.esmc.gestionte.exceptions.Exceptions;

import java.util.List;

/**
 * @author Amemorte
 * @since 05/05/2022
 */

public interface OrdreService {
    /**
     * Liste de tout les ordre
     * @return
     */
    List<Ordre> getOrdre();

    /**
     * creation d'un detailSMEnchange
     * @param ordre
     */
    public void addNewOrdre(Ordre ordre) throws Exceptions;

    /**
     * supprimer un detailSMEnchange
     * @param id
     */
    void deleteOrdre(Long id) throws Exceptions;

    /**
     * modifiacation d'un ordre
     * @param id
     * @param ordre
     * @throws Exceptions
     */

    public void updateOrdre(Long id, Ordre ordre)throws Exceptions;

    /**
     *  recherche par id
     * @param id
     * @return
     * @throws Exceptions
     */

    public Ordre getById(Long id) throws  Exceptions;



}

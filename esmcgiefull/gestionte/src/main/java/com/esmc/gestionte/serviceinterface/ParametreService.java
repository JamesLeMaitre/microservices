package com.esmc.gestionte.serviceinterface;

import com.esmc.gestionte.entities.Parametre;
import com.esmc.gestionte.exceptions.Exceptions;

import java.util.List;

public interface ParametreService {

    /**
     * Liste de tout les ordre
     * @return
     */
    List<Parametre> getParametre();

    /**
     * creation d'un promotion
     * @param parametre
     */
    public void addNewParametre(Parametre parametre) throws Exceptions;

    /**
     * supprimer une Parametre
     * @param id
     */
    void deleteParametre(Long id) throws Exceptions;

    /**
     * modifiacation d'une promotion
     * @param id
     * @param parametre
     * @throws Exceptions
     */

    public void updateParametre(Long id, Parametre parametre)throws Exceptions;

    Parametre getParametreEsmc();

    /**
     *  recherche par id
     * @param id
     * @return
     * @throws Exceptions
     */

    public Parametre getById(Long id) throws  Exceptions;
}

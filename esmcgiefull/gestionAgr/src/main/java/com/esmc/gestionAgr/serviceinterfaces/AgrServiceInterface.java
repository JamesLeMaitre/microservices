package com.esmc.gestionAgr.serviceinterfaces;

import com.esmc.gestionAgr.entities.Agr;
import com.esmc.gestionAgr.exceptions.Exceptions;

import java.util.List;

/**
 * @author Amemorte
 */
public interface AgrServiceInterface {

    /**
     * Liste des agr
     * @return
     */
   public List<Agr> getAgr();

    /**
     * creation d'un agr
     * @param agr
     */
    public Agr addNewAgr(Agr agr);

    /**
     * supprimer un agr
     * @param agrId
     */
    void deleteAgr(Long agrId) throws Exceptions;

    public void updateAgr(Long id, Agr agr)throws Exceptions;

    public Agr getById(Long id) throws  Exceptions;




}

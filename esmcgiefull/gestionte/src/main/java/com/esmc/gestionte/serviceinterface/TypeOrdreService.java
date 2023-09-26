package com.esmc.gestionte.serviceinterface;


import com.esmc.gestionte.entities.TypeOrdre;
import com.esmc.gestionte.exceptions.Exceptions;

import java.util.List;


public interface TypeOrdreService {

    /**
     * Liste des agr
     * @return
     */
    List<TypeOrdre> getTypeOrdre();

    /**
     * creation d'un detailSMEnchange
     * @param typeOrdre
     */
    public void addNewTypeOrdre(TypeOrdre typeOrdre) throws Exceptions;

    /*  public  TypeOrdre addNew(TypeOrdre typeOrdre);*/

    /**
     * supprimer un detailSMEnchange
     * @param id
     */
    void deleteTypeOrdre(Long id) throws Exceptions;

    public void updateTypeOrdre(Long id, TypeOrdre typeOrdre)throws Exceptions;

    public TypeOrdre getById(Long id) throws  Exceptions;



}

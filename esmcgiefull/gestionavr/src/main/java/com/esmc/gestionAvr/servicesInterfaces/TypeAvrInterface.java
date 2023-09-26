package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.TypeAvr;
import com.esmc.gestionAvr.exceptions.TypeAvrException;

import java.util.List;

public interface TypeAvrInterface {


    /**
     * Fonction to add new AvrType
     *
     * @param a
     * @return
     */
    public TypeAvr addTypeAvr (TypeAvr a);

    /**
     * Fonction to update an TypeAvr
     * @param id
     * @return
     */
    public TypeAvr getTypeAvrById (Long id);

    public TypeAvr updateTypeAvr(Long id, TypeAvr t);

    /**
     * Fonction to delete an TypeAvr
     * @param id
     */
    public void deleteTypeAvr (Long id);

    /**
     * Fonction to call TypeAvr list
     * @return
     */
    public List<TypeAvr> listTypeAvr();

    public List<TypeAvr> listTypeAvrOfFifo();

    TypeAvr getById(Long id) throws TypeAvrException;


}

package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.TypeLettre;

import java.util.List;

public interface TypeLettreInterface {

    /**
     * function to add TypeLetter
     * @param l
     * @return
     */
    public TypeLettre addTypeLettre (TypeLettre l);

    /**
     * function to update TypeLetter
     * @param id
     * @return
     */
    public TypeLettre getTypeLetterById ( Long id);

    /**
     * function to delete TypeLetter
     * @param id
     */
    public void deleteTypeLettre (Long id);

    /**
     * function to call list of TypeLetter
     * @return
     */
    public List<TypeLettre> listTypeLettre();

    public TypeLettre updateTypeLettre(Long id, TypeLettre t);

}

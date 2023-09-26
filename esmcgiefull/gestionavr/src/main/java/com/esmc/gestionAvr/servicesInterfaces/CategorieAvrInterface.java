package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.CategorieAvr;
import com.esmc.gestionAvr.exceptions.CategorieAvrException;

import java.util.List;

public interface CategorieAvrInterface {

    public CategorieAvr addCategorieAvr(CategorieAvr c);

    public  CategorieAvr getCategorieAvrById(Long id);

    public CategorieAvr updateCategorieAvr(Long id,CategorieAvr c);

    public void deleteCategorieAvr (Long id);

    public List<CategorieAvr> listCategorieAvr();

    CategorieAvr getById(Long id) throws CategorieAvrException;
}

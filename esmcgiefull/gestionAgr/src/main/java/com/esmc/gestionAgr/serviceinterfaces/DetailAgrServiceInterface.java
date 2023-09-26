package com.esmc.gestionAgr.serviceinterfaces;


import com.esmc.gestionAgr.entities.DetailsAgr;
import com.esmc.gestionAgr.exceptions.Exceptions;

import java.util.List;

public interface DetailAgrServiceInterface{
    /**
     * Liste des agr
     *
     * @return
     */
    public List<DetailsAgr> getAgr();


    /**
     * supprimer un agr
     *
     * @param id
     */
    public void deleteAgr(Long id) throws Exceptions;

    public void updateAgr(Long id, DetailsAgr detailAgr) throws Exceptions;

    public DetailsAgr getById(Long id);

    /**
     * creation d'un agr
     *
     * @param val2
     */
    public void affectationAgr(int val2, Long id);

    public List<DetailsAgr> findDetailAgrByIdkSU(Long idKSU);

    public DetailsAgr getDetailAgrByIdkSU(Long idKSU);

    public DetailsAgr AffectationAgr(Long idType, Long idKsu);

    DetailsAgr getDetailAgrById(Long id);

    DetailsAgr activateFRanchiseMode(Long id);
}

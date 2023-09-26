package com.esmc.gestionKsu.serviceinterfaces;

import com.esmc.gestionKsu.entities.Convention;


import java.util.List;

public interface ConventionServiceInterface {

    /**
     * Save Convention
     */
    public void saveConvention(Long idKsu) ;

    /**
     * Update Convention
     * @param id
     *
     */
    public void updateConvention(Long id);

    /**
     * Get Convention by id
     * @param id
     * @return
     */
    public Convention getConventionById(Long id);



    /**
     * Get all Conventions
     * @return
     */
    public List<Convention> getAllConvention();

    public List<Convention> listConventionParKsu(Long id);
}

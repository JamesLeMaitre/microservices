package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.SMAvr;

import java.util.List;

public interface SmAvrServiceInterface {
    public SMAvr addSMAvr(SMAvr smAvr);
    public SMAvr updateSMAvr(Long id, SMAvr smAvr);

    public void deleteSMAvr(Long id);
    public List<SMAvr> getAllSMAvr();

    public List<SMAvr> listSMAvrByTypeSmAvr(Long id);

/*    public List<SMAvr> listSmAvrBysupportMarchandEnchageId(Long id);*/
}

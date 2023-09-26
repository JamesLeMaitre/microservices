package com.esmc.gestionAvr.services;


import com.esmc.gestionAvr.entities.SMAvr;
import com.esmc.gestionAvr.repositories.SmAvrRepository;
import com.esmc.gestionAvr.servicesInterfaces.SmAvrServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class SmAvrService implements SmAvrServiceInterface {
    @Autowired
    private SmAvrRepository sMAvrRepository;

    @Override
    public SMAvr addSMAvr(SMAvr sMAvr) {
      sMAvr.setDate(new Date());
        sMAvr.setDateUpdate(new Date());



        return sMAvrRepository.save(sMAvr);
    }

    @Override
    public SMAvr updateSMAvr(Long id, SMAvr smAvr) {
        smAvr.setDateUpdate(new Date());
        return sMAvrRepository.save(smAvr);
    }

    @Override
    public void deleteSMAvr(Long id) {
        sMAvrRepository.deleteById(id);

    }

    @Override
    public List<SMAvr> getAllSMAvr() {
        return sMAvrRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<SMAvr> listSMAvrByTypeSmAvr(Long id) {
        return sMAvrRepository.listSMAvrByTypeSmAvr(id);
    }

/*    @Override
    public List<SMAvr> listSmAvrBysupportMarchandEnchageId(Long id) {
        return sMAvrRepository.listSmAvrBysupportMarchandEnchageId(id);
    }*/

}

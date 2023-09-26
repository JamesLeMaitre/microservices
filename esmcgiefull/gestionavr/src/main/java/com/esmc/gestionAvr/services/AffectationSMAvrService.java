package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.AffectationSMAvr;
import com.esmc.gestionAvr.repositories.AffectationSMAvrRepository;
import com.esmc.gestionAvr.servicesInterfaces.AffectationSMAvrInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AffectationSMAvrService implements AffectationSMAvrInterface {

    @Autowired
    private AffectationSMAvrRepository affectationSMAvrRepository;
    /**
     * @param a
     * @return
     */
    @Override
    public AffectationSMAvr addAffectation(AffectationSMAvr a) {
        return affectationSMAvrRepository.save(a);
    }

    /**
     * @param a
     * @return
     */
    @Override
    public AffectationSMAvr updateAffectationSMAvr(AffectationSMAvr a) {
        return affectationSMAvrRepository.save(a);
    }

    /**
     * @param id
     */
    @Override
    public void deleteAffectationSMAvr(Long id) {
        affectationSMAvrRepository.deleteById(id);
    }

    /**
     * @return
     */
    @Override
    public List<AffectationSMAvr> listAffectationSMAvr() {
        return affectationSMAvrRepository.findAll();
    }


    /**
     * @return
     */
    @Override
    public List<AffectationSMAvr> AffSMAvrByEchange(Long id) {
        return affectationSMAvrRepository.AffSMAvrByEchange(id);
    }

    /**
     * @return
     */
    @Override
    public List<AffectationSMAvr> AffSMAvrByTypeAvr(Long id) {
        return affectationSMAvrRepository.AffSMAvrByTypeAvr(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<AffectationSMAvr> AffSMAvrByTypeSmavr(Long id) {
        return affectationSMAvrRepository.AffSMAvrByTypeSmavr(id);
    }

    /**
     * @param id1
     * @param id2
     * @return
     */
    @Override
    public List<AffectationSMAvr> AffSMAvrByTypeAvrEchangeSMAVR(Long id1, Long id2) {
        return affectationSMAvrRepository.AffSMAvrByTypeAvrEchangeSMAVR(id1,id2);
    }

//    @Override
//    public List<AffectationSMAvr> getSmavr(String typeSmAvr, String typeAvr) {
//        return affectationSMAvrRepository.getSmavr(typeSmAvr, typeAvr);
//    }
}

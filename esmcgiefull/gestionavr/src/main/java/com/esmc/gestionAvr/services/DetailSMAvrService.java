package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.DetailSMAvr;
import com.esmc.gestionAvr.repositories.DetailSMAvrRepository;
import com.esmc.gestionAvr.servicesInterfaces.DetailSMAvrInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailSMAvrService implements DetailSMAvrInterface {

    @Autowired
    private DetailSMAvrRepository detailSMAvrRepository;
    @Override
    public DetailSMAvr addDetailSmavr(DetailSMAvr d) {
        return detailSMAvrRepository.save(d) ;
    }

    @Override
    public DetailSMAvr updateDetailSmar(DetailSMAvr d) {
        return detailSMAvrRepository.save(d);
    }

    @Override
    public void deleteDetailSMAvr(Long id) {
        detailSMAvrRepository.deleteById(id);
    }

    @Override
    public List<DetailSMAvr> ListDetailSMAvr() {
        return detailSMAvrRepository.findAll();
    }

    @Override
    public List<DetailSMAvr> ListDetailSMAvrBySMAvr(Long id) {
        return detailSMAvrRepository.DetailBySmavr(id);
    }

    @Override
    public List<DetailSMAvr> ListDetailSMAvrByAvr(Long id) {
        return detailSMAvrRepository.DetailByAvr(id);
    }
}

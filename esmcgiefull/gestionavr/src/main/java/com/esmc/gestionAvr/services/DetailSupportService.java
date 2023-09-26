package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.DetailSupport;
import com.esmc.gestionAvr.repositories.DetailSupportRepository;
import com.esmc.gestionAvr.servicesInterfaces.DetailSupportServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DetailSupportService implements DetailSupportServiceInterface {

    @Autowired
    private DetailSupportRepository detailSupportRepository;

    @Override
    public DetailSupport addDetailSupport(DetailSupport d) {
        return null;
    }

    @Override
    public DetailSupport updateDetailSmar(DetailSupport d) {
        return null;
    }

    @Override
    public void deleteDetailSupport(Long id) {

    }

    @Override
    public List<DetailSupport> ListDetailSupport() {
        return null;
    }

    @Override
    public DetailSupport getDetailSupportById(Long id) {
        return detailSupportRepository.findById(id).orElse(null);
    }
}

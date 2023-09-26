package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.PanierFifo;
import com.esmc.gestionAvr.repositories.PanierFifoRepository;
import com.esmc.gestionAvr.servicesInterfaces.PanierFifoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PanierFifoServce implements PanierFifoInterface {

    @Autowired
    public PanierFifoRepository  panierFifoRepository;

    @Override
    public PanierFifo getFirstOrAvrByType(boolean isBuy, Long idAvr) {
        return panierFifoRepository.getLastByTypeAndAvr(isBuy, idAvr, PageRequest.of(0,1));
    }

    @Override
    public List<PanierFifo> listPanierFifo(){
        return panierFifoRepository.findAll();
    }

    @Override
    public List<PanierFifo> getlistByIdDetailAgr(Long id) {
        return panierFifoRepository.getlistByIdDetailAgr(id);
    }

    @Override
    public void  deleteByIdDetailAgr(Long id) {
        panierFifoRepository.deleteById(id);
    }
}

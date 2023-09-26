package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.Fifo;
import com.esmc.gestionAvr.entities.PanierFifo;

import java.util.List;

public interface PanierFifoInterface {

    PanierFifo getFirstOrAvrByType(boolean isBuy, Long idAvr);

    public List<PanierFifo> listPanierFifo();

    List<PanierFifo> getlistByIdDetailAgr(Long id);

    void deleteByIdDetailAgr(Long id); 
}

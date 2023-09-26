package com.esmc.gestionAgr.fifo.services;


import com.esmc.gestionAgr.fifo.entities.Fifo;
import com.esmc.gestionAgr.fifo.entities.Vague;
import com.esmc.gestionAgr.fifo.utils.enums.KsuType;

import java.util.List;

public interface FifoService {

    void setNumOrder(Vague vague, KsuType ksuType);

    int setNextNumOrdreVente();

    int setNextNumOrdreAchat();

    List<Fifo> findAllByKsuType(KsuType ksuType);

    Fifo getFifoById(Long id);

     int setNextNumOrdre(KsuType ksuType);
}

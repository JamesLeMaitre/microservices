package com.esmc.gestionAgr.fifo.services;


import com.esmc.gestionAgr.fifo.entities.Avr;

import java.util.List;

public interface AvrService {
    List<Avr> listAvrByDetailAgrId(Long id);
}

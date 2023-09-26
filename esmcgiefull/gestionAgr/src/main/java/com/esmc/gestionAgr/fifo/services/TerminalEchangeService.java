package com.esmc.gestionAgr.fifo.services;


import com.esmc.gestionAgr.fifo.entities.TerminalEchange;

public interface TerminalEchangeService {
    Double bcnrPrk(Double montant);

    TerminalEchange getTeByDetailAgr(Long id);
}

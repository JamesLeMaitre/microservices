package com.esmc.gestionAgr.fifo.services;


import com.esmc.gestionAgr.fifo.entities.DetailSMEnchange;
import com.google.zxing.WriterException;

import java.io.IOException;

public interface DetailSMEchangeService {

    DetailSMEnchange mutationPourAchatFifo(Long idTeAcheteur, Long idTeVendeur, Double montant) throws IOException, WriterException;

    DetailSMEnchange mutationPourAchatFifoAdmin(Long idTeAcheteur, Long idTeVendeur, Double montant)
            throws IOException, WriterException;
}

package com.esmc.gestionAgr.fifo.models;


import com.esmc.gestionAgr.fifo.entities.Ksu;
import lombok.Data;

@Data
public class DataSupportInput {
    private Ksu infoKsuEmetteur;
    private Ksu infoKsuRecepteur;
    private PaiementInfo datePaiement;
}

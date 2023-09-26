package com.esmc.gestionAgr.fifo.models;

import lombok.Data;

@Data
public class PaiementInfo {
    private double montant;
    private String transactionId;
}

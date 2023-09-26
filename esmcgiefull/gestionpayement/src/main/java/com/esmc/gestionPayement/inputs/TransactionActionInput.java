package com.esmc.gestionPayement.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionActionInput {

    @Column(nullable = false)
    private double montant;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String prefix;

    private Long bon;

    private Long idDetailAgr;

    private Long typeFonds;

    private Long typeTransactions;

}

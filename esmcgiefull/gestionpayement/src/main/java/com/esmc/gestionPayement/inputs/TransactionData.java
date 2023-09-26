package com.esmc.gestionPayement.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionData {
    @Column(nullable = true)
    private Double amount;
    @Column(nullable = true)
    private String currency;
    @Column(nullable = true)
    private String status;
    @Column(nullable = true)
    private String payment_method;
    @Column(nullable = true)
    private String description;
    @Column(nullable = true)
    private String lot;
    @Column(nullable = true)
    private String metadata;
    @Column(nullable = true)
    private String operator_id;
    @Column(nullable = true)
    private String payment_date;
}

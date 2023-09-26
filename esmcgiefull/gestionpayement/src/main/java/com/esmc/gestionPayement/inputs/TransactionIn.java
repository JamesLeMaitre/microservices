package com.esmc.gestionPayement.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionIn {
    @Column(nullable = true)
    private Double amount;
    @Column(nullable = true)
    private String prefix;
    @Column(nullable = true)
    private String phone;
    @Column(nullable = true)
    private String client_transaction_id;
    @Column(nullable = true)
    private String notify_url;
    @Column(nullable = true)
    private String lot;
    @Column(nullable = true)
    private String code;
    @Column(nullable = true)
    private String status;
    @Column(nullable = true)
    private String treatment_status;
    @Column(nullable = true)
    private String transaction_id;
}

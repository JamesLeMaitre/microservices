package com.esmc.gestionPayement.entities;

import com.esmc.gestionPayement.inputs.ContactDecaissement;
import com.esmc.gestionPayement.inputs.PayementTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinetPayTransactionsRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String payement;

    @Column(nullable = false)
    private boolean status;
}

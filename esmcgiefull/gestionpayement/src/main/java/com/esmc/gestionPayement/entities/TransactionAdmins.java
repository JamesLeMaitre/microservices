package com.esmc.gestionPayement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionAdmins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String transactionId;

    @Column(nullable = true)
    private String reference;

    @Column(nullable = false)
    private double montant;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = true)
    private Long bon;

    @Column(name = "id_detail_agr", nullable = true)
    private Long idDetailAgr;

    @Column(name = "type_fonds", nullable = true)
    private Long typeFonds;

    @Column(nullable = false)
    private String prefix;

    private String source;

    @Column(nullable = false)
    private Long idTe;

    @Column(nullable = false)
    private Long idKsu;

    @Column(nullable = false)
    private int status =0;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }

}

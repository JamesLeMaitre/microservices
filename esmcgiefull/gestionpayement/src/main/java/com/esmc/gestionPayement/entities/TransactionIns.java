package com.esmc.gestionPayement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TransactionIns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String reference;

    @Column(nullable = false, unique = true)
    private String identifiant;

    @Column(nullable = false)
    private Double montant;

    @Column(nullable = true,length = 2000)
    private String data;

    @Column(nullable = true)
    private Long idTe;

    @Column(nullable = true)
    private Long idKsu;

    @Column(nullable = true)
    private String destination;

    @Column(nullable = true)
    private String ListOfDetailsmEnchangeLinked;

    @Column(nullable = false)
    private Boolean confirmed =false;

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

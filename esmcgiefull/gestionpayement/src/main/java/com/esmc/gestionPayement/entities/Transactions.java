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
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = true)
    private String reference;

    @Column(nullable = true)
    private String transactionId;

    @Column(nullable = true)
    private Double montant;

    @Column(nullable = true,length = 255)
    private String data;

    @Column(nullable = true)
    private Long idTe;

    @Column(nullable = true)
    private Long idKsu;

    @Column(name = "source")
    private String source;

    @Column(nullable = true)
    private String origin;

    @Column(nullable = false)
    private Boolean status =true;

    @Column(nullable = false)
    private Boolean used =false; //this attribute shows if the transaction has been used in the systemm

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

}

package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "place")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Place implements Serializable {

    @Id
    @Column(name = "num")
    private  Long num;

    @Column(name = "membre")
    private String membre;

    @Column(name = "montant")
    private String montant;

    @Column(name = "lib")
    private String lib;

    @Column(name = "datedepot")
    private String datedepot;

    @Column(name = "agence")
    private  String agence;

    @Temporal(TemporalType.TIME)
    @Column(name = "heureid")
    private Date heureId;

    @Column(name = "cais")
    private  String cais;

    @Column(name = "rembourser")
    private  boolean rembourser;

}

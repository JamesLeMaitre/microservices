package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Table(name = "compte_credit")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompteCredit implements Serializable {

    @Id
    @Column(name = "id_credit")
    private Long idCredit;

    @Column(name = "id_operation")
    private Long idOperation;

    @Column(name = "code_membre")
    private String codeMembre;

    @Column(name = "code_produit")
    private String codeProduit;

    @Column(name = "code_compte")
    private String codeCompte;

    @Column(name = "montant_place")
    private Double montantPlace;

    @Column(name = "montant_credit")
    private Double montantPredit;

    @Column(name = "datedeb")
    private Date dateDeb;

    @Column(name = "datefin")
    private Date dateFin;

    @Column(name = "source")
    private String source;

    @Column(name = "date_octroi")
    private Date dateOctroi;

    @Column(name = "compte_source")
    private String compteSource;

    @Column(name = "krr")
    private String krr;

    @Column(name = "renouveller")
    private String renouveller;

    @Column(name = "bnp")
    private Integer bnp;

    @Column(name = "domicilier")
    private Integer domicilier;

    @Column(name = "code_bnp")
    private String codeBnp;

    @Column(name = "affecter")
    private Integer affecter;

    @Column(name = "code_type_credit")
    private String codeTypeCredit;

    @Column(name = "prk")
    private Float prk;

    @Column(name = "nbre_renouvel")
    private Integer nbreRenouvel;

    @Column(name = "type_produit")
    private String typeProduit;

    @Column(name = "duree")
    private Double duree;

    @Column(name = "type_recurrent")
    private String typeRecurrent;

    @Column(name = "frequence_cumul")
    private Integer frequenceCumul;

    @Column(name = "id_bps")
    private Integer idBps;

    @Column(name = "id_agr")
    private Integer idAgr;

    @Column(name = "activer")
    private Boolean activer;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

}

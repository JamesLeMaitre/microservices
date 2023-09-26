package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ancien_compte_credit",indexes = {
        @Index(name = "fn_code",columnList = "code_membre",unique = true),
        @Index(name = "fn_code_p",columnList = "code_produit")
})
public class AncienCompteCredit implements Serializable {

    @Id
    @Column(name = "id_credit")
    private  Long idCredit;

    @Column(name = "id_operation")
    private  Long idOperation;

    @Column(name = "code_membre")
    private String codeMembre;

    @Column(name = "code_produit")
    private String codeProduit;

    @Column(name = "code_compte")
    private String codeCompte;

    @Column(name = "montant_place")
    private  double montantPlace;

    @Column(name = "montant_credit")
    private  double montantCredit;

    @Column(name = "datedeb")
    private  Date datedeb;

    @Column(name = "datefin")
    private Date datefin;

    @Column(name = "source")
    private  String source;

    @Column(name = "date_octroi")
    private  Date dateOctroi;

    @Column(name = "compte_source")
    private String compteSource;

    @Column(name = "krr")
    private  String krr;

    @Column(name = "renouveller")
    private  String renouveller;

    @Column(name = "bnp")
    private  int bnp ;

    @Column(name = "domicilier")
    private  int domicilier;

    @Column(name = "code_bnp")
    private  String codeBnp;

    @Column(name = "affecter")
    private  String affecter;

    @Column(name = "code_type_credit")
    private  String CodeTypeCredit;

    @Column(name = "prk")
    private  String prk;

    @Column(name = "desactiver")
    private  String desactiver;

    @Column(name = "rembourser")
    private  boolean rembourser;

}

package com.esmc.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PassifPresentielle implements Serializable {

    private Long id;

    private String noRef;


    private String idNumSerie;


    private String numPrinte;


    private String idChiffrementQrcode;


    private String numContratAchat;


    private String numBonCommande;


    private String numBonLivraison;


    private String nomVendeur;


    private    String prenomsvendeur;


    private  String valeurInitialXOF;


    private String valReinitBCi;


    private Date dateCreate;

    private Date dateUpdate;




}

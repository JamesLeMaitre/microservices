package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "eu_bon_neutre")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BonNeutre implements Serializable{

    @Id
    @Column(name = "bon_neutre_id")
    private Long bonNeutreId;

    @Column(name = "bon_neutre_code")
    private String bonNeutreCode;

    @Column(name = "bon_neutre_code_membre")
    private String bonNeutreCodeMembre;

    @Column(name = "bon_neutre_type")
    private String bonNeutreType;

    @Column(name = "bon_neutre_date")
    private Date bonNeutreDate;

    @Column(name = "bon_neutre_montant")
    private Double bonNeutreMontant;

    @Column(name = "bon_neutre_montant_utilise")
    private Double bonNeutreMontantUtilise;

    @Column(name = "bon_neutre_montant_solde")
    private Double bonNeutreMontantSolde;

    @Column(name = "bon_neutre_nom")
    private String bonNeutreNom;

    @Column(name = "bon_neutre_prenom")
    private String bonNeutrePrenom;

    @Column(name = " bon_neutre_raison")
    private String  bonNeutreRaison;

    @Column(name = "bon_neutre_mobile")
    private String bonNeutreMobile;

    @Column(name = "bon_neutre_email")
    private String bonNeutreEmail;

}

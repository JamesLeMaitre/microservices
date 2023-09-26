package com.esmc.gestionMembre.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "ancien_echange")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AncienEchange implements Serializable {
    @Id
    @Column(name = "id_echange")
    private Long idEchange;

    @Column(name = "type_echange")
    private  String typeEchange;

    @Column(name = "id_credit")
    private String idCredit;

    @Column(name = "cat_echange")
    private String catEchange;

    @Column(name = "code_produit")
    private  String codeProduit;

    @Column(name = "code_membre")
    private  String codeMembre;

    @Column(name = "code_compte_ech")
    private  String codeCompteEch;

    @Column(name = "montant")
    private  Double montant;

    @Column(name = "code_compte_obt")
    private  String codeCompteObt;

    @Column(name = "date_echange")
    private  Date dateEchange;

    @Column(name = "montant_echange")
    private  Double montantEchange;

    @Column(name = "agio")
    private  Double agio;

    @Column(name = "regler")
    private  Integer regler;

    @Column(name = "date_reglement")
    private  Date dateReglement;

    @Column(name = "id_utilisateur")
    private  Integer idUtilisateur;

    @Column(name = "compenser")
    private  Integer compenser;

}

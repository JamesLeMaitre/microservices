package com.esmc.gestionte.entities;


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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(indexes = @Index(name = "idx_b",columnList = "num_bon_commande"))
public class PassifPresentielle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_contrat", nullable = true)
    private String typeContrat; //1-OBPSD/OBPS 2-OT

    @Column(name = "no_ref")
    private String noRef;

    @Column(name = "id_num_serie")
    private String idNumSerie;

    @Column(name = "num_printe")
    private String numPrinte;

    @Column(name = "id_chiffrement_qrcode")
    private String idChiffrementQrcode;

    @Column(name = "num_contrat_achat")
    private String numContratAchat;

    @Column(name = "num_bon_commande")
    private String numBonCommande;

    @Column(name = "num_bon_livraison")
    private String numBonLivraison;

    @Column(name = "nom_vendeur")
    private String nomVendeur;

    @Column(name = "prenoms_vendeur")
    private String prenomsvendeur;

    @Column(name = "valeur_initial_XOF")
    private  Double valeurInitialXOF;

    @Column(name = "val_reinit_BCi")
    private Double valReinitBCi;

    @Column()
    private Boolean status=true;


    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;




}

package com.esmc.gestionAvr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author katoh
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Lettre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String adresseAcheteur;

    @Column(nullable = false)
    private  String bpAcheteur;

    @Column(nullable = false)
    private  String villeAcheteur;

    @Column(nullable = false)
    private  String telAcheteur;

    @Column(nullable = false)
    private  String emailAcheteur;

    @Column(nullable = false)
    private  String lieu;

    @Column(nullable = false)
    private  String numeroRelance;

    @Column(nullable = false)
    private  String numeroCommande;

    @Column(nullable = false)
    private  String representationStructure;

    @Column(nullable = false)
    private  String numeroBonCommande;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateCommande;

    @Column(nullable = false)
    private  String structureVendeur;

    @Column(nullable = false)
    private  String articleCommande;

    @Column(nullable = false)
    private  Double montantCommande;


    @Column(nullable = false)
    private  String numeroBonCommande2;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateLivraison;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateEcheance;

    @Column(nullable = false)
    private  String numeroFactureProFormat;

    @Column(nullable = false)
    @Lob
    private byte[] signature;

    @Column(nullable = false)
    @Lob
    private byte[] cachet;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String objet;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;


    @ManyToOne
    @JoinColumn(name = "id_type_lettre")
    private TypeLettre typeLettre;

    @ManyToOne
    @JoinColumn(name = "id_avr")
    private Avr avr;

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }

}

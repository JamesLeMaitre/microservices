package com.esmc.demandeAchatBanKsu.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "ma_ban_ksu",indexes = @Index(name = "idx_", columnList = "id, raison_social, code_membre, nom"))
public class MaBanKsu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom",nullable = true)
    private String nom;

    @Column(nullable = true)
    private String prenom;

    @Column(name = "code_membre",nullable = true)
    private String codeMembre;

    @Column(nullable = true)
    private String siteWeb;

    @Column(name = "raison_social",nullable = true)
    private String raisonSocial;

    @Column(nullable = true)
    private String codeKsuRepresentant;

    @Column(nullable = true)
    private String statusJuridique;

    @Column(name = "denomination", length = 30, nullable = true)
    private String denomination;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = true)
    private String adresse;

    @Column(name = "id_canton")
    private Long idCanton;

    @Column(nullable = true)
    private String justificationAdresse;

    @Column(nullable = true)
    private String boitePostale;

    @Column(nullable = false)
    private String email;

    private boolean etat = false;

    @Column(nullable = true)
    private  String photoJustificationAdresse;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_type_mabanksu")
    private TypeMABanKSU typeMABanKSU;

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }

//    @ManyToOne
//    @JoinColumn(name = "id_ficheodd")
//    private FicheODD ficheODD;

}

package com.esmc.gestionMembre.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "souscription")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Souscription implements Serializable {

    @Id
    @Column(name = "souscription_id")
    private Long souscriptionId;

    @Column(name = "souscription_nom")
    private String souscriptionNom;

    @Column(name = "souscription_prenom")
    private String souscriptionPrenom;

    @Column(name = "souscription_mobile")
    private String souscriptionMobile;

    @Column(name = "publier")
    private Integer publier;

    @Column(name = "souscription_membreasso")
    private Integer souscriptionMembreasso;

    @Column(name = "souscription_email")
    private String souscriptionEmail;

    @Column(name = "souscription_raison")
    private String souscriptionRaison;

    @Column(name = "souscription_numero")
    private String souscriptionNumero;

    @Column(name = "souscription_date_numero")
    private Date souscriptionDateNumero;

    @Column(name = "souscription_type")
    private String souscriptionType;

    @Column(name = "souscription_banque")
    private String souscriptionBanque;

    @Column(name = "souscription_date")
    private Date souscriptionDate;

    @Column(name = "souscription_personne")
    private String souscriptionPersonne;

    @Column(name = "souscription_montant")
    private Double souscriptionMontant;

    @Column(name = "souscription_nombre")
    private Integer souscriptionNombre;

    @Column(name = "souscription_programme")
    private String souscriptionProgramme;

    @Column(name = "souscription_type_candidat")
    private Integer souscriptionTypeCandidat;

    @Column(name = "souscription_filiere")
    private Integer souscriptionFiliere;

    @Column(name = "souscription_vignette")
    private String ssouscriptionVignette;

    @Column(name = "code_type_acteur")
    private String codeTypeActeur;

    @Column(name = "code_statut")
    private String codeStatut;

    @Column(name = "code_activite")
    private String codeActivite;

    @Column(name = "id_metier")
    private long idMetier;

    @Column(name = "id_competence")
    private long idCompetence;

    @Column(name = "souscription_ville")
    private String souscriptionVille;

    @Column(name = "souscription_quartier")
    private String souscriptionQuartier;

    @Column(name = "souscription_login")
    private String souscriptionLogin;

    @Column(name = "souscription_passe")
    private String souscriptionPasse;

    @Column(name = "souscription_souscription")
    private Integer souscriptionSouscription;

    @Column(name = "souscription_autonome")
    private Integer souscriptionAutonome;

    @Column(name = "souscription_ordre")
    private Integer souscriptionOrdre;

    @Column(name = "souscription_ancien_membre")
    private String souscriptionAncienMembre;

    @Column(name = "erreur")
    private Integer erreur;

    @Column(name = "erreurdescription")
    private String erreurdescription;

    @Column(name = "quittance_invalide")
    private Integer quittanceInvalide;

}

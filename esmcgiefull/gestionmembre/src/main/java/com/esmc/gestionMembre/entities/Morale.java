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


@Table(name = "morale")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Morale implements Serializable {

    @Id
    @Column(name = "numidentm")
    private String numIdentm;

    @Column(name = "nomm")
    private String nomm;

    @Column(name = "representant")
    private String representant;

    @Column(name = "qrt")
    private String qart;

    @Column(name = "rue")
    private String rue;

    @Column(name = "ville")
    private String ville;

    @Column(name = "bp")
    private String bp;

    @Column(name = "tel")
    private String tel;

    @Column(name = "portable")
    private String portable;

    @Column(name = "email")
    private String email;

    @Column(name = "site")
    private String site;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dateident")
    private Date dateIdent;

    @Column(name = "numcomptbp")
    private String numcomptbq;

    @Column(name = "agence")
    private String agence;

    @Column(name = "montant")
    private String montant;

    @Column(name = "heureid")
    private Date heureId;

    @Column(name = "user")
    private String user;

    @Column(name = "etat_contrat")
    private String etatContrat;

    @Column(name = "code_membre")
    private  String codeMembre;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;
}

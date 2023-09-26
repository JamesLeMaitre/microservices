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
@Table(name = "detail_mf107")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetailMf107 implements Serializable {
    @Id
    @Column(name = "id_mf107")
    private  long idMf107;

    @Column(name = "numident")
    private  long numIdent;

    @Column(name = "code_membre")
    private String codeMembre;

    @Column(name = "date_mf107")
    private  Date dateMf107;

    @Column(name = "mont_apport")
    private  Double montApport;

    @Column(name = "id_utilisateur")
    private  long idUtilisateur;

    @Column(name = "pourcentage")
    private  Float pourcentage;

    @Column(name = "proprietaire")
    private  Integer proprietaire;

    @Column(name = "creditcode")
    private  String creditcode;

    @Column(name = "nature")
    private  String nature;


    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;
}

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

@Table(name = "membre_fondateur1100")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MembreFondateur11000 implements Serializable {

    @Id
    @Column(name = "num_bon")
    private String numBon;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "tel")
    private String tel;

    @Column(name = "cel")
    private String cel;

    @Column(name = "code_membre")
    private String codeMembre;

    @Column(name = "solde")
    private String solde;

    @Column(name = "nb_repartition")
    private String nbRepartition;

    @Column(name = "id_utilisateur")
    private String idUtilisateur;



    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;
}

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


/**
 * @author Japhet
 */
@Table(name = "membre_fondateur107")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class MembreFondateur107 implements Serializable {

    @Id
    @Column(name = "numident")
    private  long numident;

    @Column(name = "nom")
    private  String nom;

    @Column(name = "prenom")
    private  String prenom;

    @Column(name = "tel")
    private  String tel;

    @Column(name = "cel")
    private  String cel;

    @Column(name = "code_membre")
    private  String codeMembre;

    @Column(name = "solde")
    private  Double solde;

    @Column(name = "nb_repartition")
    private  Integer nbRepartition;

    @Column(name = "id_utilisateur")
    private  Long idUtilisateur;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;



}

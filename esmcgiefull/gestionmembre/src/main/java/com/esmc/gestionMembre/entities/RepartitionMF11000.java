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

@Table(name = "repartition_mf1100")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RepartitionMF11000 implements Serializable {

    @Id
    @Column(name = "id_rep")
    private String idRep;

    @Column(name = "id_mf11000")
    private String idMF11000;

    @Column(name = "code_mf11000")
    private String codeMF11000;

    @Column(name = "code_membre")
    private String codeMembre;

    @Column(name = "mont_rep")
    private String montRep;

    @Column(name = "mont_reglt")
    private String montReglt;

    @Column(name = "solde_rep")
    private String soldeRep;

    @Column(name = "date_rep")
    private Date dateRep;

    @Column(name = "payer")
    private String payer;

    @Column(name = "id_utilisateur")
    private String idUtilisateur;

    @Column(name = "etat")
    private boolean etat;


    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;
}

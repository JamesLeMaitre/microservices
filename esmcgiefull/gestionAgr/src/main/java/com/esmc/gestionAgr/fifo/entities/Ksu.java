package com.esmc.gestionAgr.fifo.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ksu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    @Column(name = "code_ksu")
    private String codeKsu;
    private String codeBanKsu;
    private String nom;
    private String prenom;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNaissance;
    private String sexe;
    private String nomPere;
    @Temporal(TemporalType.DATE)
    private Date dateAchat;
    private boolean valide = false;
    private String autorisation;
    @CreationTimestamp
    private Date dateCreate;
    @UpdateTimestamp
    private Date dateUpdate;
    @Column(name = "status")
    private boolean etat = true;


    public Ksu(String nom) {
        this.nom = nom;
    }


}
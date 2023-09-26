package com.esmc.gestionMembre.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "reclamation")

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Reclamation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "systeme")
    private String systeme;

    @Column(name = "produit")
    private String produit;

    @Column(name = "code_membre")
    private String code_membre;

    @Column(name = "motif")
    private String motif;

    @Column(name = "etat")
    private boolean etat;

    @Column(length = 10000)
    private String tableau_fichier;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;







}

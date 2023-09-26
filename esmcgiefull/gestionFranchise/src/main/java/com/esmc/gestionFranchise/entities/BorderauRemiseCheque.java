package com.esmc.gestionFranchise.entities;

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
public class BorderauRemiseCheque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private  String nom;

    @Column(nullable = false)
    private  String prenom;

    @Column(nullable = false)
    private  String numeroCompte;

    @Column(nullable = false)
    private  int nombreCheque;

    @Column(nullable = false)
    private  String nomEmetteurBanque;

    @Column(nullable = false)
    private  Double montantCheque;

    @Column(nullable = false)
    private  Double montantTotal;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateDepot;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @ManyToOne
    @JoinColumn(name = "id_intervenant")
    private Intervenant intervenant;
}

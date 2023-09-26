package com.esmc.gestionContrat.entities;

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
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private  String intitule;

    @Column(nullable = false)
    private  boolean depense;

    @Column(nullable = false)
    private  Double solde;

    @Column(nullable = false)
    private  Double soldeDepart;

    @Column(nullable = false)
    private  String designation;

    @Column(nullable = false)
    private  int unite;

    @Column(nullable = false)
    private  int quantite;

    @Column(nullable = false)
    private  int prix;

    @Column(nullable = false)
    private  Double prixUnitaire;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @ManyToOne
    @JoinColumn(name = "id_typeBudget")
    private TypeBudget typeBudget;

    @ManyToOne
    @JoinColumn(name = "id_contrat")
    private Contrat contrat;

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }

}

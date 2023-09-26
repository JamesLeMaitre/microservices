package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * @author katoh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Budget implements Serializable {

    private Long id;

    private String intitule;

    private boolean depense;

    private Double solde;

    private Double soldeDepart;

    private String designation;

    private int unite;

    private int quantite;

    private int prix;

    private Double prixUnitaire;

    private Date dateCreate;

    private Date dateUpdate;

    private TypeBudget typeBudget;

    private Contrat contrat;
}

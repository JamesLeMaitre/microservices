package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


/**
 * @author katoh
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneBudgetaire implements Serializable {


    private Long id;


    private String libelle;


    private int quantite;


    private Double montant;


    private Double montantTotal;


    private Date dateCreate;


    private Date dateUpdate;

    private Intervenant intervenant;
}

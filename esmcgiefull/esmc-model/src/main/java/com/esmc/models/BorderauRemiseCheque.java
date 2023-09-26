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
public class BorderauRemiseCheque implements Serializable {

    private long id;

    private String nom;

    private String prenom;

    private String numeroCompte;

    private int nombreCheque;

    private String nomEmetteurBanque;

    private Double montantCheque;

    private Double montantTotal;

    private Date dateDepot;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

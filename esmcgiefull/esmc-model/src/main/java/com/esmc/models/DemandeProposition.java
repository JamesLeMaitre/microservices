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
public class DemandeProposition implements Serializable {

    private Long id;

    private String nom;

    private String prenom;

    private String agent;

    private String responsable;

    private String adresse;

    private String libelle;

    private int quantiteRecue;

    private String raisonSociale;

    private String bpsPropose;

    private String destination;

    private String agentResponsable;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

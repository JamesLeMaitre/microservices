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
public class Contrat implements Serializable {

    private Long id;

    private String contactFournisseur;

    private Double montantGlobalBps;

    private Double montantBci;

    private Date dateEntreVigueur;

    private Date dateFin;

    private boolean etatContrat;

    private Date dateCreate;

    private Date dateUpdate;

    private DetailsAgr detailAgr;

    private String nomEntreprise;

    private String siegeSocial;

    private String boitePostale;

    private String telephone;

    private String nomRepresentant;

    private String profession;

    private String nomPersonneMorale;

    private String domaineEntreprise;
}

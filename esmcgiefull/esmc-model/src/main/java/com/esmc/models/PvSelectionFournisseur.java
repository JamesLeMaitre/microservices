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
public class PvSelectionFournisseur implements Serializable {

    private Long id;

    private int numeroAppelOffre;

    private int nbreDossierSoumis;

    private String critereRetenus;

    private int nbrDossierAccepte;

    private int nbrDossierRejeter;

    private String nomPresidentCS;

    private String visaPresidentCS;

    private String lieuSignature;

    private Date dateSignature;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;

}

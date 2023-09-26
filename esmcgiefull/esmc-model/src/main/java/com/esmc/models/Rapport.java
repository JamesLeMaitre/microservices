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
public class Rapport implements Serializable {

    private Long id;

    private String localite;

    private String structure;

    private String numOrdre;

    private String tache;

    private String charge;

    private String observation;

    private String recommandation;

    private String rapporteur;

    private byte[] signature;

    private Date dateEmission;

    private Date date;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;

    private TypeRapport typeRapport;
}

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
public class FicheImmobilisation implements Serializable {

    private Long id;

    private String designation;

    private String nature;

    private String famille;

    private String codeIdentification;

    private String affectation;

    private String valeurAcquisition;

    private String sourceFinaancement;

    private Date dateEntree;

    private Date dateSortie;

    private Date dateCreate;

    private Date dateUpdate;

    private Immobilisation immobilisation;

    private Intervenant intervenant;
}

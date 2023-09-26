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
public class SuiviEvaluation implements Serializable {

    private Long id;

    private String nom;

    private String prenom;

    private String motif;

    private byte[] signature;

    private Date heureArrive;

    private Date dateDepart;

    private Date dateJour;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

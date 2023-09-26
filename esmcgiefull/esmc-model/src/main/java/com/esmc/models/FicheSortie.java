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
public class FicheSortie implements Serializable {

    private Long id;

    private String raisonSortie;

    private byte[] signature;

    private String objet;

    private String imputation;

    private String beneficiaire;

    private String numVehicule;

    private String position;

    private Date dateSignature;

    private Date dateLivraison;

    private Date date;

    private Date dateCreate;

    private Date dateUpdate;

}

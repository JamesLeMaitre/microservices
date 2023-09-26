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
@ToString
public class BonSortieCarburant implements Serializable {

    private Long id;

    private String imputation;

    private String beneficiaire;

    private String numVehicule;

    private String objet;

    private byte[] signature;

    private Date dateRemiseBon;

    private Date dateLivraison;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;

}

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
public class BonSortieInterne implements Serializable {

    private Long id;

    private String objet;

    private String imputation;

    private String nomBeneficiaire;

    private String numeroVehicule;

    private Date dateRemiseBon;

    private Date datelivraison;

    private Date dateSignatureAT;

    private Date dateSignatureBeneficiaire;

    private byte[] signatureAT;

    private byte[] signatureBeneficiaire;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

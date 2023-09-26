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
public class DemandeAchatTechnopole implements Serializable {

    private Long id;

    private String numero;

    private Double montantDemandeAchat;

    private Double allocationBudgetaire;

    private Double creditConsomme;

    private boolean existenceStock;

    private byte[] signatureEmeteur;

    private byte[] signatureATAchat;

    private byte[] signatureATStock;

    private byte[] signatureAFBudget;

    private byte[] signatureAG;

    private byte[] signatureAGA;

    private Date date;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

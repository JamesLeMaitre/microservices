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
public class EtatReglementEffectuer implements Serializable {

    private Long id;

    private String nom;

    private String prenom;

    private String raisonSocial;

    private String montantApayer;

    private byte[] signature;

    private Date date;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

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
public class DemandeAvanceFond implements Serializable {

    private Long id;

    private String nom;

    private String prenom;

    private String numOrdre;

    private String natureAchat;

    private String destination;

    private Double montant;

    private String fonction;

    private byte[] signature;

    private Date date;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

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
public class DetailSelectionFournisseur implements Serializable {

    private Long id;

    private String raisonSociale;

    private String nom;

    private String article;

    private Double prix;

    private int ordreClassement;

    private Date dateCreate;

    private Date dateUpdate;

    private EtatSelectionFournisseur etatSelectionFournisseur;

    private PvSelectionFournisseur pvSelectionFournisseur;
}

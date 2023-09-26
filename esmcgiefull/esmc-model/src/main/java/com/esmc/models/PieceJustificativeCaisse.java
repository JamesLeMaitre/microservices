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
public class PieceJustificativeCaisse implements Serializable {

    private Long id;

    private String numPiece;

    private Double montant;

    private String objet;

    private String numCheque;

    private Double remiseBancaire;

    private String numReleveBancaire;

    private Date date;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

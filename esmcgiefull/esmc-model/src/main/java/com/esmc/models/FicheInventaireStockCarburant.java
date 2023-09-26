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
public class FicheInventaireStockCarburant implements Serializable {

    private Long id;

    private String designationCarburant;

    private String fourniseur;

    private String numSerie;

    private Long quantiteEntree;

    private Long quantiteSortie;

    private Long stock;

    private Double valeurStock;

    private Date dateInventaire;

    private Date date;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

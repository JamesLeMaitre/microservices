package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author katoh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailMouvementStock {

    private Long id;

    private String valeur;

    private Long quantite;

    private Double prixUnitaire;

    private Date dateCreate;

    private Date dateUpdate;

    private MouvementStock mouvementStock;

    private FicheStock ficheStock;
}

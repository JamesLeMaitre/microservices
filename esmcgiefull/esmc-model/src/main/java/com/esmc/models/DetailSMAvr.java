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
public class DetailSMAvr {

    private Long id;

    private String numOrdre;

    private String reference;

    private String article;

    private int unite;

    private int quantite;

    private Double prixUnitaire;

    private Double valeur;

    private Date dateCreate;

    private Date dateUpdate;

    private SMAvr smAvr;

    private Avr avr;

}

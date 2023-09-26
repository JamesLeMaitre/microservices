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
public class Avr implements Serializable {

    private Long id;

    private String libelle;

    private int quantite;

    private Double prixUnitaire;

    private String description;

    private byte[] photo;

    private Date dateCreate;

    private Date dateUpdate;

    private CategorieAvr categorieAvr;

    private TypeAvr typeAvr;

    private DetailsAgr detailAgr;

}

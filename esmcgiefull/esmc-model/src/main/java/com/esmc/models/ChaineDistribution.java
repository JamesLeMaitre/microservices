package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


/**
 * @author katoh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChaineDistribution implements Serializable {

    private Long id;

    private String libelle;

    private Date dateCreate;

    private Date dateUpdate;

    private ChaineValeur chaineValeur;
}

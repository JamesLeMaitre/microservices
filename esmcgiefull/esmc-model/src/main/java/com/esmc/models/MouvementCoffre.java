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
public class MouvementCoffre implements Serializable {

    private Long id;

    private String libelle;

    private boolean motif;

    private Date date;

    private Date dateCreate;

    private Date dateUpdate;

    private CoffreFort coffreFort;
}

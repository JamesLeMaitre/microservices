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
public class TypeBudget implements Serializable {

    private Long id;

    private String libelle;

    private boolean etat;

    private Date dateCreate;

    private Date dateUpdate;

}

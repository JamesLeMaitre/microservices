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
public class Canton implements Serializable {

    private long id;

    private String libelle;

    private Date dateCreate;

    private Date dateUpdate;

    private Commune commune;

}

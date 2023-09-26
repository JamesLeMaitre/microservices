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
public class AgenceOdd implements Serializable {

    private Long id;

    private String entetePage;

    private String piedPage;

    private String libelle;

    private int nombre;

    private Date dateCreate;

    private Date dateUpdate;

}

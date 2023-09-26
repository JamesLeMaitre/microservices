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
public class DeatilBonSortieCarburant implements Serializable {

    private Long id;

    private String reference;

    private String article;

    private int quantite;

    private String observations;

    private Date dateCreate;

    private Date dateUpdate;

    private BonSortieCarburant bonSortieCarburant;
}

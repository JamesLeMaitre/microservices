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
public class GrilleRemuneration implements Serializable {

    private Long id;

    private Double montant;

    private String avantage;

    private Date dateCreate;

    private Date dateUpdate;

    private Tranche tranche;

    private Diplome diplome;

    private AgentRecruteur agentRecruteur;
}

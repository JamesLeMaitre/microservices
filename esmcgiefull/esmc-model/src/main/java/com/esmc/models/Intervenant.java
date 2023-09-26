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
public class Intervenant implements Serializable {

    private Long id;

    private String libelle;

    private boolean etat;

    private Date dateCreate;

    private Date dateUpdate;

    private TableDescriptionEp tableDescriptionEp;

    private AgentRecruteur agentRecruteur;
}
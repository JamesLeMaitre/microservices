package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgentPoste {

    private Long idAgentPoste;


    private Long idFranchise;

    private Long idKsu;

    private Long idDetailAgrFranchise;

    private Poste poste;


    private AgentRecruteur agentRecruteur;


    private Date dateCreate;


    private Date dateUpdate;


}

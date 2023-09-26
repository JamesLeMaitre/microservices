package com.esmc.gestionFranchise.entities.organev2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgentPosteView {
    private Long id;

    private String codePouvoirFaire;

    private String poste;

}



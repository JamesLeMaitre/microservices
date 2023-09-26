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
public class DetailConseilAdministration implements Serializable {

    private Long id;

    private Date dateCreate;

    private Date dateUpdate;

    private ConseilAdministration conseilAdministration;

    private AgentRecruteur agentRecruteur;
}

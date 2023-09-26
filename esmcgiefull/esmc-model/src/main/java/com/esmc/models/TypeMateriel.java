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
public class TypeMateriel implements Serializable {

    private Long id;

    private String projet;

    private String ordre;

    private String termesReferences;

    private String agentResponsables;

    private Date dateCreate;

    private Date dateUpdate;

}

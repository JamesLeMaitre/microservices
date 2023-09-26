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
public class TermeReference implements Serializable {

    private Long id;

    private String projet;

    private String ordre;

    private String termesReferences;

    private String agentResponsable;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

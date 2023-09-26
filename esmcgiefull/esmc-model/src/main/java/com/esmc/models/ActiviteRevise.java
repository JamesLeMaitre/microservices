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
public class ActiviteRevise implements Serializable {

    private Long id;

    private String activite;

    private String periodeinitiale;

    private String responsable;

    private String periodeRevue;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

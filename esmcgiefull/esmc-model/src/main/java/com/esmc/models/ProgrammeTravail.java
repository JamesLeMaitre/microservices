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
public class ProgrammeTravail implements Serializable {

    private long id;

    private String numOrdre;

    private String objectif;

    private String tache;

    private String periode;

    private String responsable;

    private String observation;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

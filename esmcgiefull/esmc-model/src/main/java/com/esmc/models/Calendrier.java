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
public class Calendrier implements Serializable {

    private Long id;

    private String periode;

    private String activite;

    private String responsable;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

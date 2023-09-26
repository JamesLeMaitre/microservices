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
public class FournisseurRestreint implements Serializable {

    private Long id;

    private String raisonSocilae;

    private String destination;

    private String contacts;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

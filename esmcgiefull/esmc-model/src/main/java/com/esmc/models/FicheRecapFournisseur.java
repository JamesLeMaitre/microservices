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
public class FicheRecapFournisseur implements Serializable {

    private Long id;

    private String raisonSociale;

    private String bps;

    private String contact;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

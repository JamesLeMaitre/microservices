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
public class EtatControlCaisse implements Serializable {

    private Long id;

    private String motifs;

    private Double depense;

    private Double solde;

    private Date date;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

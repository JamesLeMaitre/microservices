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
public class EtatPrevisionnelDepense implements Serializable {

    private Long id;

    private Date dateDebutPeriode;

    private Date dateFinPeriode;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;

    private Depense depense;
}

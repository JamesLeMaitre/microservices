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
public class FicheArreteCaisse implements Serializable {

    private Long id;

    private Double soldeTheorique;

    private Double soldePhysique;

    private Date dateInventaire;

    private Date dateCreate;

    private Date dateUpdate;

    private EspeceMonetaire especeMonetaire;

    private Intervenant intervenant;
}

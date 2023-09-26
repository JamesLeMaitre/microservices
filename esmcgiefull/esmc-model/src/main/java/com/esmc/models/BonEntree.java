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
public class BonEntree implements Serializable {

    private Long id;

    private String typeMateriels;

    private String categorieMateriel;

    private String descriptionMateriel;

    private String nomProprietaire;

    private String raisonSociale;

    private String franchise;

    private String ksu;

    private String raisonEntree;

    private String visaAgentReception;

    private String visaDestinataire;

    private String visaProprietaire;

    private Date date;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

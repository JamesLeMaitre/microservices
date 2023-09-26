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
public class FicheInventaireStock implements Serializable {

    private Long id;

    private String fournisseur;

    private String numeroInventaire;

    private String designationBPS;

    private Double valeurActuelle;

    private Double cout;

    private byte[] signatureAgentStock;

    private byte[] signatureAgentGestionStock;

    private Long numeroSerie;

    private Date dateacquisition;

    private Date date;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

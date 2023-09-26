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
public class DetailSMEnchange implements Serializable {

    private Long id;

    private String codeSM;

    private Double coutUnitaire;

    private int quantite;

    private Double total;

    private Double sourceEnree;

    private Double sourcceSortie;

    private Double fondsEntre;

    private Double fondsFondDisponible;

    private Double fondsSortie;

    private String codeBar;

    private Long refer=null;

    private String sourceMEV;

    private Date dateAchat;

    private Date dateCreate;

    private Date dateUpdate;

    private TerminalEchange terminalEchange;

    private SupportMarchandEnchage supportMarchandEnchage;

}

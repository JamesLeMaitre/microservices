package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Roger
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ordre implements Serializable {

    private Long id;

    private String numeroOPI;

    private String codeOPI;

    private String numeroFacture;

    private Double montantOPI;

    private Date datePrelevementOPI;

    private String codeBar;

    private Date dateEmission;

    private String entetePage;

    private String piedPage;

    private String signature;

    private String numeroCompteBancaire;

    private Double sourceEnree;

    private Double sourcceSortie;

    private Double fondsEntre;

    private Double fondsSortie;

    private Double fondsFondDisponible;

    private TypeOrdre typeOrdre;

    private SupportMarchandEnchage supportMarchandEnchage;

    private TerminalEchange terminalEchange;

    private Long refer;

    private Date dateCreate;

    private Date dateUpdate;


}

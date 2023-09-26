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
public class TableauBordTrimestriel implements Serializable {

    private Long id;

    private String destinationBudget;

    private Double depenseTrimestrielle;

    private Double soldeTrimestrielle;

    private Double budgetTrimestrielle;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

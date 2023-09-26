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
public class ActiviteBudgetAssocie implements Serializable {

    private Long id;

    private String activite;

    private String periode;

    private String responsable;

    private String budgetPeriodique;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

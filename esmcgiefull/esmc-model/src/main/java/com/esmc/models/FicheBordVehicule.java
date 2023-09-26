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
public class FicheBordVehicule implements Serializable {

    private Long id;

    private String numero;

    private String natureVehicule;

    private String immatriculation;

    private String destination;

    private float kilometrage;

    private int carburant;

    private Date heureSortie;

    private Date dateEntree;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

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
public class RegistreCheque implements Serializable {

    private Long id;

    private String numeroCheque;

    private String motifs;

    private Date dateEmission;

    private Date dateEndossement;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

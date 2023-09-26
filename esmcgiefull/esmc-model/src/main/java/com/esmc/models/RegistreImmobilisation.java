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
public class RegistreImmobilisation implements Serializable {

    private Long id;

    private String numero;

    private String designation;

    private Double valeur;

    private String observations;

    private Date dateAchat;

    private Date dateReception;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

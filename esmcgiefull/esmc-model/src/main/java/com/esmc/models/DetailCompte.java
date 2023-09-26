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
public class DetailCompte implements Serializable {

    private Long id;

    private String libelle;

    private Double debit;

    private Double credit;

    private Date dateCreate;

    private Date dateUpdate;

    private Compte compte;

    private RapprochementBancaire rapprochementBancaire;
}

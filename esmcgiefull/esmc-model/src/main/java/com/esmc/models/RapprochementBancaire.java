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
public class RapprochementBancaire implements Serializable {

    private Long id;

    private String libelle;

    private String banque;

    private Date dateExercie;

    private Date dateRapprochement;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

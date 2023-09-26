package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppelCandidature {

    private Long id;


    private String libelle;


    private String description;


    private Long idDetailAgrFranchiser;


    private String etat;


    private boolean publier;


    private Date dateCreation;


    private Date dateUpdate;
}

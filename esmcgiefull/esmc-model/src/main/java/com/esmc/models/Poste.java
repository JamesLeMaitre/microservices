package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Poste {

    private Long id;

    private String libelle;

    private String description;

    private Structure structure;

    private Poste posteParent;

    private Boolean status=false;


    private Date dateCreate;


    private Date dateUpdate;



}

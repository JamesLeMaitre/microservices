package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Releve implements Serializable {

    private Long id;


    private String type;


    private String membre;


    private String ficher;

    private String origine;


    private Boolean valider = Boolean.FALSE;


    private Boolean cloture = Boolean.FALSE;


    private Double montant;


    private Long ksu;


    private Long terminalEchange;


    private Date dateCreate;


    private Date dateUpdate;


}

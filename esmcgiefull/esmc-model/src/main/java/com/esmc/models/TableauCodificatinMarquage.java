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
public class TableauCodificatinMarquage implements Serializable {

    private Long id;

    private String codeNature;

    private String numeroOrde;

    private String anneeAccqisition;

    private String codeGieEsmc;

    private String codeLocalisation;

    private String codeFinancement;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;

    private TypeMateriel typeMateriel;
}

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
public class DetailFicheSortie implements Serializable {

    private Long id;

    private String reference;

    private String article;

    private Long quantite;

    private String observation;

    private Date dateCreate;

    private Date dateUpdate;

    private TypeMateriel typeMateriel;

    private FicheSortie ficheSortie;
}

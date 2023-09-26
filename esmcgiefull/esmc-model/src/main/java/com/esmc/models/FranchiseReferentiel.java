package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FranchiseReferentiel  implements Serializable {

    private long id;

    private Long idFranchise;

    private int stage;

    private String label;

    private int typeDecoupage;
    /**
     * 1 - Geographique
     * 2 - Centre
     */
}

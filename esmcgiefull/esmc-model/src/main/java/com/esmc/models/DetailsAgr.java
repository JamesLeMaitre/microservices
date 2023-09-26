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
public class DetailsAgr implements Serializable {

    private Long id;

    private boolean etat;

    private boolean isFranchise=false;

    private Date dateCreate;

    private Date dateUpdate;

    private TypeMaBanKsuAgr typeMaBanKsuAgr;

    private Long ksu;

}

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
public class CoffreFort implements Serializable {

    private Long id;

    private String numero;

    private boolean motif;

    private String code;

    private Date dateCreate;

    private Date dateUpdate;

    private Intervenant intervenant;
}

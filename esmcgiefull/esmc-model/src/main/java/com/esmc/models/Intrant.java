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
public class Intrant implements Serializable {

    private Long id;

    private boolean supportEtablie;

    private boolean archive;

    private Long ksu;

    private Date dateCreate;

    private Date dateUpdate;

    private Avr avr;

}

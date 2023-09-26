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
public class Fifo implements Serializable {

    private Long id;

    private int numOrdre;

    private Date dateCreate;

    private Date dateUpdate;

    private DetailsAgr detailAgr;

    private FicheODD ficheODD;
}

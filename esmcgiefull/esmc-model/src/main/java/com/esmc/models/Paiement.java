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
public class Paiement implements Serializable {

    private Long id;

    private String referencePaiement;

    private Date datePaiement;

    private Date dateCreate;

    private Date dateUpdate;

    private TypePayement typePaiement;

    private MaBanKsu maBanKsu;

    private DetailsAgr detailAgr;
}

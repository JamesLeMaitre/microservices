package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@Data
public class Panier implements Serializable {

    private  Long id;

    private int quantite;

    private boolean etat = false;

    private Long ksu;

    private Avr avr;

    private Date dateCreate;

    private Date dateUpdate;

}

package com.esmc.gestionAgr.fifo.models;


import com.esmc.gestionAgr.fifo.entities.Avr;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferenceModel {
    String libelle;
    String timestamp;
    Long idKsu;
    Double value;
    private Long id;
    private boolean etat = true;
    private String codeRef;
    private String refTokenFront;
    private Date dateCreate;
    private Date dateUpdate;
    private Avr avr;
    private Long idRefParent;
}
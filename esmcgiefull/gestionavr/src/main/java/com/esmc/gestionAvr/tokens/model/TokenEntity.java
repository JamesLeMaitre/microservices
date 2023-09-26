package com.esmc.gestionAvr.tokens.model;

import com.esmc.gestionAvr.entities.Avr;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenEntity implements Serializable {
    String libelle;
    String timestamp;
    Long idKsu;
    Double value;
    private Avr avr;
    private Long idRefParent;
}
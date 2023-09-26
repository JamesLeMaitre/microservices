package com.esmc.gestionAgr.fifo.models;


import com.esmc.gestionAgr.fifo.entities.Avr;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenEntity {
    String libelle;
    String timestamp;
    Long idKsu;
    Double value;
    private Avr avr;
    private Long idRefParent;
}

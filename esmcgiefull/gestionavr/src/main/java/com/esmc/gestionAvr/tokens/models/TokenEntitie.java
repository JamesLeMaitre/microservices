package com.esmc.gestionAvr.tokens.models;



import com.esmc.gestionAvr.entities.Avr;
import lombok.Data;



@Data
public class TokenEntitie {
    String libelle;
    String timestamp;
    Long idKsu;
    Double value;
    private Avr avr;
    private Long idRefParent;




}

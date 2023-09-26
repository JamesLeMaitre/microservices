package com.esmc.gestionAvr.inputs;

import com.esmc.gestionAvr.entities.Avr;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ExtrantInput {
    private Long idTypeSupport;
    private String data;
    private Long detailAgrEmetteur;
    private Long detailAgrRecepteur;
    private Long refer;
}

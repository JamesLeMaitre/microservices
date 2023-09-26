package com.esmc.gestionAvr.tokens.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {
    private String libelle;
    private Long idksu;
    private Double value;
    private Token[] tokens;
}

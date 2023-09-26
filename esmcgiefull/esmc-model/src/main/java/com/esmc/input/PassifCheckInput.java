package com.esmc.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassifCheckInput {
    private String numContratAchat;

    private String numBonCommande;

    private String numero;
}
package com.esmc.gestionAvr.tokens.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenInput implements Serializable {
    private Long[] idReferencToken;
    private Long idDetailAgr;
}

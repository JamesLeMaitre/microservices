package com.esmc.gestionMembre.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result <V,T> {
    private V key;
    private T value;
}

package com.esmc.gestionPayement.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Input {
    private String codePays;
    private String telephone;
    private String clientSecret;

}

package com.esmc.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KsuCheckInput {
    private String lastName;
    private String firstName;
    private String numero;
    private String raisonSocial;
}

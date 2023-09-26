package com.esmc.gestionCertification.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PanierInput {

    private Long idPoste;

    private Long idDetailAgr;

    private Long idDetailAgrFranchiser;

    private String libellePoste;

    private String data;
}

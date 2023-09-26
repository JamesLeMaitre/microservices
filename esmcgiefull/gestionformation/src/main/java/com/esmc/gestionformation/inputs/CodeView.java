package com.esmc.gestionformation.inputs;

import com.esmc.gestionformation.entities.AffectationSalle;
import com.esmc.models.Ksu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeView {
    private Long id;
    @Column(length = 20000000)
    private Ksu ksu;

    private String code;
    private AffectationSalle affectionSalle;
    private String raisonSociale;

    private String poste;
}

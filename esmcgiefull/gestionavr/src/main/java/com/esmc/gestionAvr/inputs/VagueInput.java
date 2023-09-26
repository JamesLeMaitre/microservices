package com.esmc.gestionAvr.inputs;

import com.esmc.gestionAvr.entities.Vague;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VagueInput {
    @Column(nullable = true)
    private String label;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private Double limitAmount=0.0;

    @Column(nullable = true)
    private Long nextVague=null;

    @Column(nullable = true)
    private Long idPromotion=null;

    @Column(nullable = true)
    private Double numerator=0.0;

    @Column(nullable = true)
    private Double denominator=1.0;
}

package com.esmc.gestionContrat.entities.annexes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grille {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrille;
    private String poste;
    private String niveau_etude_competence;
    private String tranche1_debutant;
    private String tranche2_junior;
    private String tranche3_senior;
    private String avantages;
    private Long idDetailsAgr;
}

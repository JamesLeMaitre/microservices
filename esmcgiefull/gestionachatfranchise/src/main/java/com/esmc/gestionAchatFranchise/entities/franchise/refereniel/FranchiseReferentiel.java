package com.esmc.gestionAchatFranchise.entities.franchise.refereniel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FranchiseReferentiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private Long idFranchise;

    private Long idDetailAgr;

    @Column(nullable = true)
    private int stage;

    @Column(nullable = true)
    private String label;

    @Column(nullable = true)
    private int typeDecoupage;
    /**
     * 1 - Geographique
     * 2 - Centre
     */
}

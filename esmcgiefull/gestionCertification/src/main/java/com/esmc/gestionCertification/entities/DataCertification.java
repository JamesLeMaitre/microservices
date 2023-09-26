package com.esmc.gestionCertification.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataCertification {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = true, length = 12291456)
    private String data;
    /*firts commit*/
    @Column(name = "id_poste")
    private Long idPoste;

    @Column(name = "id_detail_agr")
    private Long idDetailAgr;

    @ManyToOne
    @JoinColumn(name = "panier_certification_id")
    private PanierCertification panierCertification;
}

package com.esmc.gestionCertification.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "panier_certification",indexes = @Index(name = "idx_pn",columnList = "id_poste,id_detail_agr,id_detail_agr_franchiser"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PanierCertification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_poste")
    private Long idPoste;

    @Column(name = "id_detail_agr")
    private Long idDetailAgr;

    @Column(name = "id_detail_agr_franchiser")
    private Long idDetailAgrFranchiser;

    // Definit Oui = Afficher , Non = Non affichable
    private Boolean view;

    @Column(name = "libelle_poste")
    private String libellePoste;

    @CreationTimestamp
    @Column(name = "date_create")
    private Date dateCreate;

    @UpdateTimestamp
    @Column(name = "date_update")
    private Date dateUpdate;
}

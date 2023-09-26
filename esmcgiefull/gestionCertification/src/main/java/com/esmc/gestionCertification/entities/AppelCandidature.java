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

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppelCandidature implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false)
    private String description;

    @Column(name = "id_detail_agr_franchiser")
    private Long idDetailAgrFranchiser;

    @Column(nullable = false)
    private String etat;

    @Column(nullable = false)
    private boolean publier;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreation;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;


}

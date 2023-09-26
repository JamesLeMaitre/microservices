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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Affectation implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_candidature")
    private AppelCandidature candidature;

    private int places;

    @Column(name = "id_poste")
    private Long idPoste;

    @Column(name = "id_detail_agr_franchiser")
    private Long idDetailAgrFranchiser;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreation;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;


    class Input{
        private Long idPoste;
    }



}

package com.esmc.gestionformation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "salles")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class  AffectationSalle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_agr")
    private Long idAgr;

    @Column(name = "idksu")
    private Long idksu;

    @ManyToOne
    @JoinColumn(name = "id_type_salle")
    private TypeSalles typeSalle;

    @ManyToOne
    @JoinColumn(name = "id_salle_formation")
    private SalleFormation salleFormation;

    private Long idDetailAgrFranchiser;
    private Long idPoste;
    private Long candidature;
    private String formation;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

}

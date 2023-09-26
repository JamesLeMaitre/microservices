package com.esmc.gestionformation.entities;

import com.esmc.models.Ksu;
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
@Table(indexes = @Index(name = "idx_",columnList = "id_agr_franchiser"))
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Code implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @ManyToOne
    @JoinColumn(name = "id_type_code")
    private TypeCodes typeCode;

    @Column(name = "id_agr")
    private Long idAgr;
    @Column(name = "id_agr_franchiser")
    private Long idAgrFranchiser;

    @ManyToOne
    @JoinColumn(name = "id_affectation_salle")
    private AffectationSalle affectationSalle;

    @Column(nullable = false)
    private boolean etat;

    @Column(nullable = false)
    private boolean viewAdmin =false;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

}

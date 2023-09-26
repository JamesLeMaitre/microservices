package com.esmc.gestionAchatFranchise.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


/**
 * @author katoh
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(indexes = @Index(name = "idx_",columnList = "id_detail_agr"))
public class Canton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean statusVente;


    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;


    @ManyToOne
    @JoinColumn(name = "id_commune")
    private Commune commune;

    @Column(nullable = true,name = "id_detail_agr")
    private Long idDetailAgr;

    @Column(nullable = true)
    private Long[] partners;

    @Column(nullable = true)
    private Boolean isBuy=false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date dateBuy;


}

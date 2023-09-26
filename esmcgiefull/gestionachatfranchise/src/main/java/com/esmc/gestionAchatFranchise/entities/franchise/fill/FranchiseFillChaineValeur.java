package com.esmc.gestionAchatFranchise.entities.franchise.fill;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineValeur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FranchiseFillChaineValeur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Long idInstitution;

    @Column(nullable = true)
    private Long ids;

    @Column(nullable = true)
    private Long idKsu;

    @Column(nullable = true)
    private Long idDetailAgr;

    @Column(nullable = true)
    private Long stage=2L;

    @Column(nullable = true)
    private Long[] partners;

    @ManyToOne
    @JoinColumn(nullable = true)
    private FillChaineValeur fillChaineValeur;

    @Column(nullable = true)
    private Boolean status=false;

    @Column(nullable = true)
    private Boolean isBuy=false;

    @Column()
    private String image;

    @Column(nullable = true)
    private String libelle;

    @Column()
    private String code;

    @Column(length = 5000)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date dateBuy;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateCreate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateUpdate;

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }
}
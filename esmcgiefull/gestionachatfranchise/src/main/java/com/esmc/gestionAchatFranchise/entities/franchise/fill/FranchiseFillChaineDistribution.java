package com.esmc.gestionAchatFranchise.entities.franchise.fill;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.felm.fill.FillInstitution;
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
public class FranchiseFillChaineDistribution implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Long idChaineValeur;

    @Column(nullable = true)
    private String libelle;

    @Column(nullable = true)
    private String code;

    @Column(nullable = true)
    private String image;

    @Column(length = 5000)
    private String description;

    @Column(nullable = true)
    private Long idDetailAgr;

    @Column(nullable = true)
    private Long stage=1L;

    @Column(nullable = true)
    private Long[] partners;

    @ManyToOne
    @JoinColumn(nullable = true)
    private FillChaineDistribution fillChaineDistribution;

    @Column(nullable = true)
    private Boolean status=false;

    @Column(nullable = true)
    private Boolean isBuy=false;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateCreate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateUpdate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date dateBuy;

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }
}

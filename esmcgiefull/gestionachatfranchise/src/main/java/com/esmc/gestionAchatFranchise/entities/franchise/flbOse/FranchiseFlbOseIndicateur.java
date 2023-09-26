package com.esmc.gestionAchatFranchise.entities.franchise.flbOse;

import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseIndicateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FranchiseFlbOseIndicateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private Long idAgenceOdd;

    @Column(nullable = true)
    private String libelle;

    @Column()
    private String code;

    @Column()
    private String image;

    @Column(length = 5000)
    private String description;

    @Column(nullable = true)
    private Long idDetailAgr;

    @Column(nullable = true)
    private Long[] partners;

    @Column(nullable = true)
    private Long stage=8L;

    @ManyToOne
    @JoinColumn(nullable = false)
    private FlbOseIndicateur flbOseIndicateur;

    @Column(nullable = true)
    private Boolean status=false;

    @Column(nullable = true)
    private Boolean isBuy=false;

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
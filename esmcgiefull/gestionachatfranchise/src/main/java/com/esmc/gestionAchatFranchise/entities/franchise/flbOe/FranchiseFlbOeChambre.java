package com.esmc.gestionAchatFranchise.entities.franchise.flbOe;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillInstitution;
import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChambre;
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
public class FranchiseFlbOeChambre  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = true)
    private String libelle;

    @Column()
    private String code;

    @Column()
    private String image;

    @Column(nullable = true)
    private Long idCible;

    @Column(nullable = false)
    private Long idIndicateur;

    @Column(nullable = true)
    private Long idDetailAgr;

    @Column(nullable = true)
    private Long stage=6L;

    @Column(nullable = true)
    private Long[] partners;

    @ManyToOne
    @JoinColumn(nullable = true)
    private FlbOeChambre flbOeChambre;

    @Column(nullable = true)
    private Boolean status=false;

    @Column(nullable = true)
    private Boolean isBuy=false;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date dateBuy;


    @Column(length = 5000)
    private String description;

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
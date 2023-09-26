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
public class Pays {

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


    @Column(nullable = true)
    private Long idDetailAgr;

    @Column(nullable = true)
    private Long[] partners;

    @Column(nullable = true)
    private Boolean isBuy=false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date dateBuy;

    @Column()
    private String photo;



    @ManyToOne
    @JoinColumn(name = "id_zone_monnetaire")
    private ZoneMonnetaire zoneMonnetaire;

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }
}

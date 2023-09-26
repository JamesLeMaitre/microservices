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
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String entetePage;

    @Column(nullable = true)
    private String piedPage;

    @Column(nullable = true)
    private String codeBan;

    @Column(nullable = true)
    private String codeBci;

    @Column(nullable = true)
    private String organisations;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @ManyToOne
    @JoinColumn(name = "id_type_franchise")
    private TypeFranchise typeFranchise;

    @Column(name = "id_detail_agr")
    private Long detailAgr;

    @ManyToOne
    @JoinColumn(name = "id_centre_franchise")
    private CentreFranchise centreFranchise;

    public Franchise(Long id, TypeFranchise typeFranchise, Long detailAgr) {
        this.id = id;
        this.typeFranchise = typeFranchise;
        this.detailAgr = detailAgr;
    }

    public Franchise(TypeFranchise typeFranchise, Long detailAgr) {
        this.typeFranchise = typeFranchise;
        this.detailAgr = detailAgr;
    }

//    @PrePersist
//    private void setDateTime() {
//        dateCreate = dateUpdate = new Date();
//    }
//
//    @PreUpdate
//    private void updateDateTime() {
//        dateUpdate = new Date();
//    }
}

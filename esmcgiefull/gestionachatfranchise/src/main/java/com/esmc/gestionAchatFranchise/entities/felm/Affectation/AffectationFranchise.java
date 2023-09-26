package com.esmc.gestionAchatFranchise.entities.felm.Affectation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AffectationFranchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idTypeProduitParent = null;

    @Column(nullable = false)
    private Long idElementParent = null;

    @Column(nullable = false)
    private Long idTypeProduitChild = null;

    @Column(nullable = false)
    private Long idElementChild = null;

    @Column(nullable = false)
    private Long idKsuOwner = null;

    @Column(nullable = false)
    private int state = 0;

    @Column(nullable = false)
    private int percent = 0;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
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

package com.esmc.gestionAvr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vague {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String label;

    @Column(nullable = true, length = 500)
    private String description;

    @Column(nullable = true)
    private Double limitAmount=0.0;

    @Column(nullable = true)
    private Double currentAccumulateAmount=0.0;

    @Column(nullable = true)
    private Double allAccumulateAmount=0.0;

    @Column(nullable = true)
    private Boolean status= false;

    //calculus parameter
    @Column(nullable = true)
    private Double numerator=1.0;

    @Column(nullable = true)
    private Double denominator=1.0;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;


    @Column(name = "idPromotion", nullable = true)
    private Long idPromotion;

    @Column(name = "nextVague", nullable = true)
    private Long nextVague;


    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }

}

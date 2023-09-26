package com.esmc.gestionAgr.fifo.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Vague implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String label;

    @Column(nullable = true, length = 500)
    private String description;

    @Column(nullable = true)
    private Double limitAmount = 0.0;

    @Column(nullable = true)
    private Double currentAccumulateAmount = 0.0;

    @Column(nullable = true)
    private Double allAccumulateAmount = 0.0;

    @Column(nullable = true)
    private Boolean status = false;

    //calculus parameter
    @Column(nullable = true)
    private Double numerator = 1.0;

    @Column(nullable = true)
    private Double denominator = 1.0;

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

    @OneToMany(mappedBy = "vague", orphanRemoval = true)
    private Collection<Fifo> fifos = new ArrayList<>();
}

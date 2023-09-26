package com.esmc.gestionAvr.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRegistryValue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String identifant;

    private Long  refer;

    @Column(nullable = true)
    private Long  idKsu;

    @Column(nullable = true)
    private double  value = 0.0;

    @Column(nullable = true)
    private Boolean status= true;

    @Column(nullable = true)
    private String description;
    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date dateUse;

}

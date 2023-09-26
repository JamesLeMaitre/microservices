package com.esmc.gestionAgr.fifo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRegistryValue implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identifant;
    private Long refer;
    private Long idKsu;
    private double value = 0.0;
    private Boolean status = true;
    private String description;
    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;
    @UpdateTimestamp
    private Date dateUpdate;
    @CreationTimestamp
    private Date dateUse;

    public ProductRegistryValue(Long idKsu, double value, Boolean status, String description) {
        this.idKsu = idKsu;
        this.value = value;
        this.status = status;
        this.description = description;
    }
}
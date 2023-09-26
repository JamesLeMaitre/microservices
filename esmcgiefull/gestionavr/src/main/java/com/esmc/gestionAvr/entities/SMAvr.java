package com.esmc.gestionAvr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author katoh
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SMAvr implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private  String codeSMAvr;

    @Column(nullable = true)
    private  String libelle;

    @Column(nullable = true)
    private  String lieu;

    @Column(nullable = true)
    private  String entre;

    @Column(nullable = true)
    private  String ente;

    @Column(nullable = true)
    @Lob
    private  byte[] signature;

    @Column(nullable = true)
    private  String piedPage;

    @Column(name = "cacher", nullable = true)
    private  String cacher;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date date;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @Column(name = "id_support_marchand_enchage")
    private Long supportMarchandEnchage;

    @ManyToOne
    @JoinColumn(name = "id_type_smAvr")
    private TypeSmAvr typeSmAvr;

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }

}

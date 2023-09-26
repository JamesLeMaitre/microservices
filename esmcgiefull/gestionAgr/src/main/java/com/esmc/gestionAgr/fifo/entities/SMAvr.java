package com.esmc.gestionAgr.fifo.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SMAvr implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String codeSMAvr;

    private String libelle;

    private String lieu;

    private String entre;

    private String ente;

    @Lob
    private byte[] signature;
    private String piedPage;

    @Column(name = "cacher", nullable = true)
    private String cacher;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date date;

    @CreationTimestamp
    private Date dateCreate;

    @UpdateTimestamp
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
package com.esmc.gestionAgr.fifo.entities;

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
public class SupportUtilise implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;
    private String indicatif;
    private String code;

    @ManyToOne
    private TableDescriptionEp tableDescriptionEp;

    @CreationTimestamp
    private Date dateCreation;

    @CreationTimestamp()
    @Column(nullable = false, updatable = false)
    private Date dateCreate;

    @UpdateTimestamp()
    @Column(nullable = false)
    private Date dateUpdate;
}

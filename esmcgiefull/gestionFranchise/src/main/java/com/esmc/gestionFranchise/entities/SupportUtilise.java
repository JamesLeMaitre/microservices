package com.esmc.gestionFranchise.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupportUtilise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;
    private String indicatif;
    private String code;

    @ManyToOne
    private TableDescriptionEp tableDescriptionEp;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreation;

    @CreationTimestamp()
    @Column(nullable = false,updatable = false)
    private Date dateCreate;

    @UpdateTimestamp()
    @Column(nullable = false)
    private Date dateUpdate;
}

package com.esmc.gestionAgr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "agr",indexes = @Index(name = "idx_",columnList = "libelle"))
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agr implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,name = "libelle")
    private String libelle;

    @Column
    private String description;

    @Column(name = "code", length = 10)
    private String Code;

    private boolean etat = false;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

}

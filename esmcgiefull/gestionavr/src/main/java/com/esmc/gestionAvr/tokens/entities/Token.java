package com.esmc.gestionAvr.tokens.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Token implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idKsu;
    private String tokens;
    private double value_token;

    private String libelle;

    private boolean state = false;

    @CreationTimestamp
    private Date dateCreate;

    @UpdateTimestamp
    private Date dateUpdate;
}

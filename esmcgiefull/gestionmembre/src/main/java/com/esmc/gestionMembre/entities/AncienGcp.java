package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "ancien_gcp")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AncienGcp implements Serializable {
    @Id
    @Column(name = "id_gcp")
    private  Integer idGcp;

    @Column(name = "code_tegc")
    private  String codeTegc;

    @Column(name = "code_cat")
    private String codeCat;

    @Column(name = "code_membre")
    private String codeMembre;

    @Column(name = "id_credit")
    private Integer idCredit;

    @Column(name = "source")
    private String source;

    @Column(name = "date_conso")
    private  Date dateConso;

    @Column(name = "mont_gcp")
    private  Double montGcp;

    @Column(name = "mont_preleve")
    private Double montPreleve;

    @Column(name = "reste")
    private  Double reste;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;
}

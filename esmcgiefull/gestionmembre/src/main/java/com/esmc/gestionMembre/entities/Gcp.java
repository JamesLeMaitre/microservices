package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Table(name = "gcp")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Gcp implements Serializable {

    @Id
    private Long idGcp;

    @Column(name = "code_membre")
    private String codeMembre;

    @Column(name = "date_conso")
    private Date dateConso;

    @Column(name = "mont_gcp")
    private Double montGcp;

    @Column(name = "mont_preleve")
    private Double montPreleve;

    @Column(name = "reste")
    private Double reste;

    @Column(name = "source")
    private String source;

    @Column(name = "code_cat")
    private String codeCat;

    @Column(name = "id_credit")
    private Integer idCredit;

    @Column(name = "id_agr")
    private Integer idAgr;

    @Column(name = "code_tegc")
    private String codeTegc;

    @Column(name = "bon_id")
    private Integer bonId;

    @Column(name = "type_gcp")
    private String typeGcp;

}

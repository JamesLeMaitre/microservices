package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "credit")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Credit implements Serializable {

    @Id
    @Column(name = "codecredi")
    private  String codecredi;

    @Column(name = "montantcredi")
    private  Float montantcredi;

    @Column(name = "membre")
    private String membre;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "montplace")
    private  Double montplace;

    @Column(name = "datefin")
    private  String datefin;

    @Column(name = "datedeb")
    private  String datedeb;

    @Column(name = "val")
    private Float val;

    @Column(name = "periode")
    private  String periode;

    @Column(name = "source")
    private  String source;

    @Column(name = "dateoctroi")
    private  String dateoctroi;

    @Column(name = "agence")
    private  String agence;

    @Column(name = "inn")
    private  String inn;

}

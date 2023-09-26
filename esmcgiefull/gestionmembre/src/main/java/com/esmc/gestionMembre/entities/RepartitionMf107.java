package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Table(name = "repartition_mf107")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RepartitionMf107 implements Serializable {

    @Id
    @Column(name = "id_rep")
    private  Long idRep;

    @Column(name = "id_mf107")
    private  long idMf107;

    @Column(name = "code_membre")
    private  String codeMembre;

    @Column(name = "date_rep")
    private  Date dateRep;

    @Column(name = "mont_rep")
    private  Double montRep;

    @Column(name = "mont_reglt")
    private  Double montReglt;

    @Column(name = "solde_rep")
    private  Double soldeRep;

    @Column(name = "payer")
    private  Integer payer;


}

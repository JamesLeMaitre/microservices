package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "eu_traite")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Traite implements Serializable {
    @Id
    @Column(name = "id_traite")
    private Long idTraite;

    @Column(name = "traite_tegcp")
    private int traiteTegcp;

    @Column(name = "bon_type")
    private String bonType;

    @Column(name = "traiter")
    private int traiter;

    @Column(name = "traite_code_banque")
    private String traiteCodeBanque;

    @Column(name = "bon_id")
    private int bonId;

    @Column(name = "traite_date_debut")
    private Date traiteDateDebut;

    @Column(name = "traite_date_fin")
    private Date traiteDateFin;

    @Column(name = "traite_disponible")
    private int traiteDisponible;

    @Column(name = "traite_imprimer")
    private int traiteImprimer;

    @Column(name = "traite_escompte_nature")
    private int vtraiteEscompteNature;

    @Column(name = "traite_numero")
    private String traiteNumero;

    @Column(name = "traite_montant")
    private double traiteMontant;

    @Column(name = "traite_payer")
    private int traitePayer;


}

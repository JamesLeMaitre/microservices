package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Table(name = "tpagcp")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tpagcp implements Serializable {

    @Id
    @Column(name = "id_tpagcp")
    private Long idTpagcp;

    private String codeMembre;

    @Column(name = "date_deb")
    private Date dateDeb;

    @Column(name = "date_deb_tranche")
    private Date dateDebTranche;

    @Column(name = "date_fin")
    private Date dateFin;

    @Column(name = "date_fin_tranche")
    private Date dateFinTranche;

    @Column(name = "mont_echange")
    private Double montEchange;

    @Column(name = "mont_echu")
    private Double montEchu;

    @Column(name = "mont_escompte")
    private Double montEscompte;

    @Column(name = "mont_gcp")
    private Double montGcp;

    @Column(name = "mont_tranche")
    private Double montTranche;

    @Column(name = "ntf")
    private double ntf;

    @Column(name = "periode")
    private double periode;

    @Column(name = "reste_ntf")
    private double resteNtf;

    @Column(name = "solde")
    private Double solde;

    @Column(name = "code_compte")
    private String codeCompte;

    @Column(name = "escomptable")
    private int escomptable;

    @Column(name = "mode_reglement")
    private String modeReglement;

    @Column(name = "type_ressource")
    private String typeRessource;

    @Column(name = "mont_gcp_maj")
    private double montGcpMaj;

    @Column(name = "type_bl")
    private String typeBl;

    @Column(name = "numero_bl")
    private String numeroBl;

    @Column(name = "reinjecter", nullable = false)
    private int reinjecter;

    @Column(name = "nbre_injection", nullable = false)
    private int nbreInjection;
}

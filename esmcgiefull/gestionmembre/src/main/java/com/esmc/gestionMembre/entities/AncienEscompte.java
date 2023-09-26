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


@Table(name = "ancien_escompte")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AncienEscompte implements Serializable {
    @Id
    @Column(name = "id_escompte")
    private  long idEscompte;

    @Column(name = "code_membre")
    private String codeMembre;

    @Column(name = "code_compte")
    private String codeCompte;

    @Column(name = "montant")
    private  Double montant;

    @Column(name = "ntf")
    private Integer ntf;

    @Column(name = "montant_tranche")
    private Double montantTranche;

    @Column(name = "date_deb")
    private Date dateDeb;

    @Column(name = "periode")
    private Integer periode;

    @Column(name = "date_fin")
    private  Date dateFin;

    @Column(name = "mont_echu")
    private Double montEchu;

    @Column(name = "date_deb_tranche")
    private  Date dateDebTranche;


    @Column(name = "date_fin_tranche")
    private  Date dateFinTranche;

    @Column(name = "mont_echu_transferer")
    private Double montEchuTransferer;

    @Column(name = "solde")
    private Double solde;

    @Column(name = "reste_ntf")
    private Double resteNtf;

    @Column(name = "id_echange")
    private long idEchange;

    @Column(name = "code_membre_benef")
    private String codeMembreBenef;

    @Column(name = "date_escompte")
    private Date dateEscompte;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;
}

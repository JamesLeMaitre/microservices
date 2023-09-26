package com.esmc.gestionMembre.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "eu_bon_neutre_detail")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BonNeutreDetail implements Serializable {

    @Id
    @Column(name = "bon_neutre_detail_id", nullable = false)
    private Long bonNeutreDetailId;

    @Column(name = "bon_neutre_id")
    private Long bonNeutre;

    @Column(name="bon_neutre_detail_code")
    private String bonNeutreDetailCode;

    @Column(name="bon_neutre_detail_date")
    private Date bonNeutreDetailDate;

    @Column(name="bon_neutre_detail_montant")
    private Double bonNeutreDetailMontant;

    @Column(name="bon_neutre_detail_montant_utilise")
    private Double bonNeutreDetailMontantUtilise;

    @Column(name="bon_neutre_detail_montant_solde")
    private Double bonNeutreDetailMontantSolde;

    @Column(name="bon_neutre_detail_banque")
    private String bonNeutreDetailBanque;

    @Column(name="bon_neutre_detail_numero")
    private String bonNeutreDetailNumero;

    @Column(name="bon_neutre_detail_date_numero")
    private Date bonNeutreDetailDateNumero;

}

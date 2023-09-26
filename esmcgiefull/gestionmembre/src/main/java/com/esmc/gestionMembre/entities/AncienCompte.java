package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "eu_ancien_compte")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AncienCompte implements Serializable {

    @Id
    private String code_compte;

    @Column(name = "code_membre", length = 25)
    private String codeMembre;

    @Column(name = "mifare_card", length = 254)
    private String MifareCard;

    @Column(name = "lib_compte", length = 50)
    private String lib_compte;

    @Column(name = "solde")
    private Double solde;

    @Column(name = "date_alloc")
    @Temporal(TemporalType.DATE)
    private Date date_alloc;

    @Column(name = "code_type_compte", length = 4)
    private String code_type_compte;

    @Column(name = "code_cat", length = 15)
    private String code_cat;

    @Column(name = "desactiver", length = 1)
    private boolean desactiver;

    @Column(name = "card_printed_date", length = 15)
    private String CardPrintedDate;

    @Column(name = "card_printed_id_date", length = 11)
    private int CardPrintedIDDate;

}

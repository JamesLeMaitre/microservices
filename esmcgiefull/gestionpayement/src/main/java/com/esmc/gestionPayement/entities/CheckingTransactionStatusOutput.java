package com.esmc.gestionPayement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.mapping.Array;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CheckingTransactionStatusOutput implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer total;

    private Array data;

    private String refTmoney;

    private String dateHeureTmoney;

    private String idRequete;

    private String statutRequete;

    private String typeRequete;

    private String dateHeureRequete;

    private String refCommande;

    private Integer montant;

    private String numeroClient;

    private String idPartenaire;

    private String nomPartenaire;
}

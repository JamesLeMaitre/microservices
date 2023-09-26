package com.esmc.gestionMembre.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
@Table(name = "ancienSmsmoney")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AncienSmsmoney {
    @Id
    @Column(name = "")
    private  String NEng;

    @Column(name = "")
    private  Integer fromAccount;

    @Column(name = "")
    private Integer destAccount;

    @Column(name = "")
    private String creditcode;

    @Column(name = "")
    private  float creditAmount;

    @Column(name = "")
    private  Integer sentTo;

    @Column(name = "Motif")
    private String motif;

    @Column(name = "")
    private  String currencyCode;

    @Column(name = "")
    private  String DateTime;

    @Column(name = "")
    private  String idDateTime;

    @Column(name = "")
    private  String DateTimeConsumed;

    @Column(name = "")
    private  Integer idDateTimeConsumed;

    @Column(name = "")
    private  Integer DestAccountConsumed;

    @Column(name = "")
    private  String codeAgence;

    @Column(name = "")
    private  Integer utilisateur;

    @Column(name = "")
    private  Integer idUtilisateur;

    @Column(name = "")
    private  String numReçu;



    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;
}

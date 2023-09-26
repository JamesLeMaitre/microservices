package com.esmc.gestionAvr.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author katoh
  * @update JamesLeMaitre
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Intrant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private boolean supportEtablie;

    @Column(nullable = true)
    private boolean archive;

    @Column(nullable = true)
    private Double montant;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    private String dataInfo;

    private boolean viewDev = false;

    @ManyToOne
    @JoinColumn(name = "id_avr",nullable = true)
    private Avr avr;

    @Column(name = "refer" ,nullable = true)
    private Long refer;

    @Column(name = "id_detailagr_emetteur" ,nullable = true)
    private Long detailAgrEmetteur;

    @Column(name = "id_detailagr_recepteur" ,nullable = true)
    private Long detailAgrRecepteur;

    @Column(name = "id_ksu" ,nullable = true)
    private Long ksu;

    @Column(name = "id_ksu_emetteur" ,nullable = true)
    private Long ksuEmetteur;

    @Column(name = "id_ksu_recepteur" ,nullable = true)
    private Long ksuRecepteur;

    @Column(name = "id_poste_emetteur" ,nullable = true)
    private Long posteEmetteur;

    @Column(name = "id_poste_recepteur" ,nullable = true)
    private Long posteReceveur;

    @Column(name = "id_poste_recepteur_other" ,nullable = true)
    private Long posteReceveurOther;


    @ManyToOne
    @JoinColumn(name = "id_detail_type_support", nullable = true)
    private DetailTypeSupport detailTypeSupport;

    @ManyToOne
    @JoinColumn(name = "id_detail_support")
    private DetailSupport detailSupport;

    @ManyToOne
    @JoinColumn(name = "id_type_smAvr")
    private TypeSmAvr typeSmAvr;


}

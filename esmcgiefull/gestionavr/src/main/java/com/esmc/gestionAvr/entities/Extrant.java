package com.esmc.gestionAvr.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author katoh
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(indexes = @Index(name = "idx_",columnList = "id_poste_emetteur"))
public class Extrant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private boolean supportEtablie;

    @Column(nullable = true)
    private boolean archive;

    @Column(name = "refer" ,nullable = true)
    private Long refer;

    @Column(nullable = true)
    private Double montant;

    @ManyToOne
    @JoinColumn(name = "id_avr",nullable = true)
    private Avr avr;

    @Column(name = "id_ksu" ,nullable = true)
    private Long ksu;

    @Column(name = "id_ksu_emetteur" ,nullable = true)
    private Long ksuEmetteur;

    @Column(name = "id_ksu_recepteur" ,nullable = true)
    private Long ksuRecepteur;

    @Column(name = "id_detailagr_emetteur" ,nullable = true)
    private Long detailAgrEmetteur;

    @Column(name = "id_detailagr_recepteur" ,nullable = true)
    private Long detailAgrRecepteur;

    @Column(name = "id_poste_emetteur" ,nullable = true)
    private Long posteEmetteur;

    @Column(name = "id_poste_recepteur" ,nullable = true)
    private Long posteReceveur;


    private String dataInfo;

    private boolean viewDev = false;

    @Column(name = "id_poste_recepteur_other" ,nullable = true)
    private Long posteReceveurOther;

    @ManyToOne
    @JoinColumn(name = "id_type_smAvr")
    private TypeSmAvr typeSmAvr;

    @ManyToOne
    @JoinColumn(name = "id_detail_support",nullable = true)
    private DetailSupport detailSupport;

    @ManyToOne
    @JoinColumn(name = "id_detail_type_support", nullable = true)
    private DetailTypeSupport detailTypeSupport;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;




}

package com.esmc.gestionAvr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "affectionsmavr",indexes = @Index(name = "idx_",columnList = "id_type_smavr,id_type_avr,id_echange,etat"))
public class AffectationSMAvr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_type_smavr")
    private TypeSmAvr typeSmAvr;

    @ManyToOne
    @JoinColumn(name = "id_echange")
    private Echange echange;

    @ManyToOne
    @JoinColumn(name = "id_type_avr")
    private TypeAvr typeAvr;


    @Column(nullable = true, updatable = false)
    @CreationTimestamp
    private Date dateCreate;


    @Column(nullable = true)
    @UpdateTimestamp
    private Date dateUpdate;

    @Column(nullable = true,name = "etat")
    private Boolean etat=false;

    @Column(nullable = true)
    private int etape;


}

package com.esmc.gestionformation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_salle_formation")
    private SalleFormation salleFormation;

    private Long idPoste;

    private String libellePoste;

    @ManyToOne
    @JoinColumn(name = "id_code")
    private Code code;

    private boolean dailyCheck = false;
    private Long idDetailsAgr;

    private Long idKsu;

    private String nomksu;

    private String dateEntree;
    private String dateSortie;
    @CreationTimestamp
    private Date dateCreate;
    @UpdateTimestamp
    private Date dateUpdate;
}

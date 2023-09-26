package com.esmc.gestionMembre.entities;

import com.esmc.models.Ksu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "releve_passif")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Releve implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "membre")
    private String membre;

    @Column(name = "fichier")
    private String ficher;

    @Column(name = "origine")
    private String origine;

    @Column(name = "valide")
    private Boolean valider = Boolean.FALSE;

    @Column(name = "cloture")
    private Boolean cloture = Boolean.FALSE;

    @Column(name = "montant")
    private Double montant;

    @Column(name = "id_ksu")
    private Long ksu;

    @Column(name = "id_terminal_echange")
    private Long terminalEchange;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }


}

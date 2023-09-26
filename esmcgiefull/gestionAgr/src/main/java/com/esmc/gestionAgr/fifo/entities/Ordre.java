package com.esmc.gestionAgr.fifo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Ordre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String numeroOPI;

    @Column(nullable = false)
    private String codeOPI;

    @Column(nullable = true)
    private String numeroFacture;

    @Column(nullable = false)
    private Double montantOPI;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date datePrelevementOPI;

    @Column(nullable = false)
    private String codeBar;

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    private Date dateEmission;


    @Column(nullable = true)
    private String signature;

    @Column(nullable = true)
    private String numeroCompteBancaire;

    @Column(name = "source_entree", nullable = true)
    private Double sourceEnree;

    @Column(name = "source_sortie", nullable = false)
    private Double sourcceSortie;

    @Column(name = "fonds_entre", nullable = false)
    private Double fondsEntre;

    @Column(name = "fonds_sortie")
    private Double fondsSortie = 0.0;

    @Column(name = "fonds_fond_disponible")
    private Double fondsFondDisponible = 0.0;

    @Column(nullable = true)
    private Long refer = 0L;

    @ManyToOne
    @JoinColumn(name = "id_type_ordre")
    private TypeOrdre typeOrdre;

    @ManyToOne
    @JoinColumn(name = "id_sme")
    private SupportMarchandEnchage supportMarchandEnchage;

    @ManyToOne
    @JoinColumn(name = "id_te")
    private TerminalEchange terminalEchange;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;
}

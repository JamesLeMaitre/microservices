package com.esmc.gestionte.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author katoh
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(indexes = @Index(name = "idx_",columnList = "id_terminal_echange,id_support_marchand_enchage,refer"))
public class DetailSMEnchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codeSM;

    @Column(nullable = false)
    private Double coutUnitaire;

    @Column(nullable = false)
    private int quantite;

    @Column(nullable = true)
    private boolean status=true;

    @Column(nullable = true)
    private boolean used=false;

    @Column(nullable = false)
    private Double total;

    @Column(name = "source_entree", nullable = false)
    private Double sourceEnree;

    @Column(name = "source_sortie", nullable = false)
    private Double sourcceSortie;

    @Column(name = "fonds_entre", nullable = false)
    private Double fondsEntre;

    @Column(name = "fonds_sortie")
    private Double fondsSortie = 0.0;

    @Column(name = "fonds_fond_disponible")
    private Double fondsFondDisponible = 0.0;

    @Column(nullable = false)
    private String codeBar;

    private String orgine;

    @Column(nullable = true,name = "refer")
    private Long refer= 0L;

   @CreationTimestamp
    @Column(nullable = false)
    private Date dateAchat;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @Column(nullable = true)
    private Long idEtiquette;

    @ManyToOne
    @JoinColumn(name = "id_terminal_echange")
    private TerminalEchange terminalEchange;

    @ManyToOne
    @JoinColumn(name = "id_support_marchand_enchage")
    private SupportMarchandEnchage supportMarchandEnchage;

}

package com.esmc.gestionAvr.entities;

import com.esmc.gestionAvr.tokens.entities.ReferencToken;
import com.esmc.gestionAvr.utils.enums.KsuType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fifo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int numOrdreVente = 0;
    private int numOrdreAchat = 0;
    private int numOrdre = 0;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @Column(name = "id_detail_agr")
    private Long detailAgr;

    @ManyToOne
    @JoinColumn(name = "avr_id")
    private Avr avr;

    private String description;

    // Tu es au tour
    private boolean panierTour = false;


    /**
     *
     * Complementary Variable
     * defaultAmountFirstType  //Ban Value (vendeur init amount)
     * defaultAmountSecondType //Bci Value  (acheteur init amount)
     * actionAmountFirstType  (en Ban)// the amount to be used during action
     * actionAmountSecondType (en Bci)//  the amount to be used during action
     *
     * */

    private Double  defaultAmountFirstType=0.0;

    private Double  defaultAmountSecondType=0.0;

    private Double  actionAmountFirstType=0.0;

    private Double  actionAmountSecondType=0.0;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_registry_value_id")
    private ProductRegistryValue productRegistryValue;

    @Column(nullable = true)
    private Boolean endStatus=false;

    @Column(nullable = false)
    private Boolean isBuy=true;
    private boolean isTreated = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "referenc_token_id")
    private ReferencToken referencToken;

    private KsuType ksuType;

    @ManyToOne
    @JoinColumn(name = "vague_id")
    private Vague vague;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ksu_id")
    private Ksu ksu;
    private double negativeAmount = 0.0;
    private double amount = 0.0;

    private Date processingDate;

}

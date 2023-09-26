package com.esmc.gestionAgr.fifo.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "history_fifo")
public class HistoryFiFo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Ksu ksu;

    private double amount;

    @Column(name = "id_detail_agr")
    private Long detailAgr;

    @ManyToOne
    @JoinColumn(name = "avr")
    private Avr avr;


    /**
     * Complementary Variable
     * defaultAmountFirstType  //Ban Value (vendeur init amount)
     * defaultAmountSecondType //Bci Value  (acheteur init amount)
     * actionAmountFirstType  (en Ban)// the amount to be used during action
     * actionAmountSecondType (en Bci)//  the amount to be used during action
     */
    @Column(nullable = true)
    private Double defaultAmountFirstType = 0.0;
    @Column(nullable = true)
    private Double defaultAmountSecondType = 0.0;
    @Column(nullable = true)
    private Double actionAmountFirstType = 0.0;
    @Column(nullable = true)
    private Double actionAmountSecondType = 0.0;

    @ManyToOne
    private ProductRegistryValue productRegistryValue = null;

    private Boolean endStatus = false;


    @Column(nullable = false)
    private Boolean isBuy = true;

    @CreationTimestamp
    private Date dateCreate;

    @UpdateTimestamp
    private Date dateUpdate;
}

package com.esmc.gestionAvr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FifoHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int numOrdreAchat;

    @Column(nullable = false)
    private int numOrdreVente;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;


    /**
     * Commenter cet attribut après
     */
    @Column(name = "id_detail_agr")
    private Long detailAgr;

    @ManyToOne
    @JoinColumn(name = "avr")
    private Avr avr;

//    @ManyToOne
//    @JoinColumn(name = "id_fichr_odd")
//    private FicheODD ficheODD;



    /**
     *
     * Complementary Variable
     * defaultAmountFirstType  //Ban Value (vendeur init amount)
     * defaultAmountSecondType //Bci Value  (acheteur init amount)
     * actionAmountFirstType  (en Ban)// the amount to be used during action
     * actionAmountSecondType (en Bci)//  the amount to be used during action
     *
     * */
    @Column(nullable = true)
    private Double  defaultAmountFirstType=0.0;
    @Column(nullable = true)
    private Double  defaultAmountSecondType=0.0;
    @Column(nullable = true)
    private Double  actionAmountFirstType=0.0;
    @Column(nullable = true)
    private Double  actionAmountSecondType=0.0;

    @ManyToOne
    private ProductRegistryValue productRegistryValue = null;

    @Column(nullable = true)
    private Boolean endStatus=false;

    @Column(nullable = false)
    private Boolean isBuy=true;
}

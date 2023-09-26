package com.esmc.demandeAchatBanKsu.entities;

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
public class MABanOI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String referenceMABanOksuOI;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTransaction;


    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;


    @ManyToOne
    @JoinColumn(name = "id_ma_ban_ksu")
    private MaBanKsu maBanKsu;

}

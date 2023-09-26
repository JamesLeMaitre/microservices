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
public class BanKsu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String codeBanKsu;

    @Column(nullable = true)
    private int quantite;

    @Column(nullable = true)
    private int total;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date dateAchat;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @ManyToOne
    @JoinColumn(name = "id_ma_ban_ksu")
    private MaBanKsu maBanKsu;

    @Column(name = "id_support_marchand_enchage")
    private Long supportMarchandEnchage;

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }

}

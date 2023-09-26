package com.esmc.gestionAchatFranchise.entities.felm.flbOe;

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
public class FlbOeChaineDistribution  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private Long idChaineValeur;
    @Column(nullable = false)
    private String libelle;

    @Column()
    private String code;

    @Column()
    private String image;

    @Column(length = 5000)
    private String description;

    @Column()
    private Boolean status=false;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

}

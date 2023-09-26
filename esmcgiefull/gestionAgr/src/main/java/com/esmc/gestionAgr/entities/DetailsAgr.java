package com.esmc.gestionAgr.entities;

import lombok.AllArgsConstructor;
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
@Table(name = "details_agr",indexes = @Index(name = "idx_",columnList = "id_ksu,id_type_ma_ban_ksu_agr"))
public class DetailsAgr implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private boolean etat;

    @Column(nullable = true)
    private boolean isFranchise=false;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @ManyToOne
    @JoinColumn(name = "id_type_ma_ban_ksu_agr")
    private TypeMaBanKsuAgr typeMaBanKsuAgr;

    @Column(name = "id_ksu")
    private Long ksu;

    public DetailsAgr(TypeMaBanKsuAgr typeMaBanKsuAgr, Long ksu) {
        this.typeMaBanKsuAgr = typeMaBanKsuAgr;
        this.ksu = ksu;
    }
}

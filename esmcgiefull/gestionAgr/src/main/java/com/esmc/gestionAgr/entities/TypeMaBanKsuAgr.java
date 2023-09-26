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
@Table(name = "type_ma_ban_ksu_agr",indexes = @Index(name = "idx_",columnList = "id_ma_ban_ksu,id_agr"))
public class TypeMaBanKsuAgr implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_ma_ban_ksu")
    private Long IdMaBanKsu;

    @ManyToOne
    @JoinColumn(name = "id_agr")
    private Agr agr;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

}

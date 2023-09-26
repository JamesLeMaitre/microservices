package com.esmc.gestionAgr.fifo.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TypeSmAvr implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String libelle;

    @Column(nullable = true)
    private String code;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;
}

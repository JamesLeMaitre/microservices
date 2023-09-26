package com.esmc.gestionte.entities;

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
public class PassifPresentielleHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Long idPassifPresentielle;

    @Column(nullable = true)
    private double montant = 0.0;

    @Column(nullable = true, length = 3000)
    private String description;

    @Column(nullable = true, length = 200)
    private String numero;

    @Column(nullable = true, length = 200)
    private String identifiant;

    @Column()
    private Boolean status=false;

    @Column()
    private Boolean useRequest=false;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

}

package com.esmc.gestionPayement.entities.coris;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Coris {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = true)
    private String transactionId;

    @Column(nullable = true)
    private String montant;


    @Column(nullable = true)
    private String codeRef;



    @Column(nullable = true)
    private Long idTe;

    @Column(nullable = true)
    private Long idKsu;



    @Column(nullable = false)
    private Boolean status =true;


    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;
}

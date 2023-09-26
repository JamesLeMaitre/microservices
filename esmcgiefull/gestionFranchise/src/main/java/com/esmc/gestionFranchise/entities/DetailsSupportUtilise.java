package com.esmc.gestionFranchise.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DetailsSupportUtilise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String libelle;

    @ManyToOne
    private SupportUtilise supportUtilise;

    @ManyToOne
    private TableDescriptionEp tableDescriptionEp;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;
}

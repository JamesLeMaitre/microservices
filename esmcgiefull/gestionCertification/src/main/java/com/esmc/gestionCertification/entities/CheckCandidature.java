package com.esmc.gestionCertification.entities;

/** @uthor JamesLeMaitre
 * le 28/11/2022 à 16:24
 */

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
@NoArgsConstructor
@AllArgsConstructor
public class CheckCandidature implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private AppelCandidature candidature;
    private Long  idDetailAgr;

    private Long  idPoste;
    private Long  detailsAgrFranchiser;

    private Boolean checkstatus;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreation;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;
}

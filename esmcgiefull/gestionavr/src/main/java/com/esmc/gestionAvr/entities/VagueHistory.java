package com.esmc.gestionAvr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VagueHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "vague", nullable = false)
    private Vague vague;


    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private Date startDate;

    @UpdateTimestamp
    @Column(nullable = true)
    private Date endDate=null;


    @Temporal(TemporalType.DATE)
    @Column(nullable = false,updatable = false)
    private Date dateCreate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateUpdate;



    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = startDate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }
}

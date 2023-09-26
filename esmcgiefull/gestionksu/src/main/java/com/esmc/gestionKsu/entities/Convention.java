package com.esmc.gestionKsu.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Convention class
 * @author katoh <katohdavid@gmail.com>
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Convention implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;
    
    @ManyToOne
    @JoinColumn(name = "id_ksu")
    private Ksu ksu;

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }

}

package com.esmc.gestionte.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author katoh
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TerminalEchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;

    @Column(name = "id_detail_agr")
    private Long detailAgr;

    public TerminalEchange(Date dateCreate, Date dateUpdate, Long detailAgr) {
        this.dateCreate = dateCreate;
        this.dateUpdate = dateUpdate;
        this.detailAgr = detailAgr;
    }

    public TerminalEchange(Long id) {
        this.id = id;
    }

    @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }
}

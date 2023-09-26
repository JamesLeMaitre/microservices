package com.esmc.gestionAgr.fifo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableCaching
@Builder
@Table(name = "poste")
public class Poste implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true)
    private Long id;

    @Column(name = "libelle", nullable = true)
    private String libelle;

    @Column(name = "code", nullable = true, unique = true)
    private String code;

    @Column(nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Structure structure;

    @ManyToOne
    @JoinColumn(name = "poste_parent_id")
    @JsonIgnore
    private Poste posteParent;

/*    @ManyToOne
    private TypeSupport typeSupport;*/

    @Column(nullable = false)
    private Boolean status = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate;


}

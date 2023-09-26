package com.esmc.gestionFranchise.entities.organev2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EnableCaching
@ToString
@Table(name = "poste" ,indexes = @Index(name = "fn_idx_libelle",columnList = "libelle, poste_parent_id,id,code "))
public class Poste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true)
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "code", unique = true)
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
    private Boolean status=false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdate;




}

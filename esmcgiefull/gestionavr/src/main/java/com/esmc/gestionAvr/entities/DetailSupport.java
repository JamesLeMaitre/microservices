package com.esmc.gestionAvr.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(indexes = @Index(name = "idx_", columnList = "id_type_support"))
public class DetailSupport  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true,name = "id_type_support")
    private Long idTypeSupport;

    @Column(name = "libelle_type_support", length = 100, nullable = false)
    private String libelleTypeSupport;
    
    @Column(name = "code", nullable = false)
    private String code;

    @CreationTimestamp
    @Column(nullable = false)
    private Date dateCreate;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date dateUpdate;



}

package com.esmc.gestionAvr.putonchain.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String wording;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "unit_of_measure_id", referencedColumnName = "id")
    private UnitOfMeasure unitOfMeasure;

    private String code;

    @CreationTimestamp
    private Date dateCreate;

    @CreationTimestamp
    private Date dateUpdate;
}

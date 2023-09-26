package com.esmc.gestionAvr.putonchain.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@Table(name = "unitofmeasures")
@NoArgsConstructor
@AllArgsConstructor
public class UnitOfMeasure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String wording;
    private String code;

    @CreationTimestamp
    private Date createDate;

    @UpdateTimestamp
    private Date updateDate;
}

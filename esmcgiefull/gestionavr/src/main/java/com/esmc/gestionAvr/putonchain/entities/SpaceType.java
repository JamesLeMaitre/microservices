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
@Table(name = "spacetypes")
@NoArgsConstructor
@AllArgsConstructor
public class SpaceType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String wording;
    @CreationTimestamp
    private Date dateCreate;

    @CreationTimestamp
    private Date dateUpdate;

}

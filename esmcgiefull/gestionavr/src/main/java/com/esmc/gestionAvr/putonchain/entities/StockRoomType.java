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
@Table(name = "stockroomtypes")
@NoArgsConstructor
@AllArgsConstructor
public class StockRoomType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String wording;
    @CreationTimestamp
    private Date dateCreate;

    @CreationTimestamp
    private Date dateUpdate;

    @ManyToOne
    @JoinColumn(name = "center_type_id")
    private CenterType centerType;
}

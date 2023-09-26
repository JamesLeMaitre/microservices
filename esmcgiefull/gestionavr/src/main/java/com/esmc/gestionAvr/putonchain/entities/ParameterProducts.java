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
@Table(name="parametersproducts")
@NoArgsConstructor
@AllArgsConstructor
public class ParameterProducts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String wording;

    @CreationTimestamp
    private Date dateCreate;

    @CreationTimestamp
    private Date dateUpdate;

    private int minimumQuantity;
    private int maximumQuantity;


    @ManyToOne
    @JoinColumn(name = "stock_room_type_id")
    private StockRoomType stockRoomType;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}

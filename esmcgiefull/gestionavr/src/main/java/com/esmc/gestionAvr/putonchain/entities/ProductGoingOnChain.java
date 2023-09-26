package com.esmc.gestionAvr.putonchain.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "productsgoingonchain")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductGoingOnChain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parameter_products_id")
    private ParameterProducts parameterProducts;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private Products products;

    @ManyToOne
    @JoinColumn(name = "stock_room_type_id")
    private StockRoomType  stockRoomType;

    @ManyToOne
    @JoinColumn(name = "space_type_id")
    private SpaceType spaceType;

    private Long idDetailAgr;

    private int quantity;

    private boolean state = true;
}

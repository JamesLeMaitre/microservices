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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Products implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String wording;

    @Column(nullable = false)
    private int quantity;

    private double unitPrice;

    private String description;
    private String  filePath;

    private boolean state;

    private double unitTotal;

    @CreationTimestamp
    private Date dateCreate;

    @UpdateTimestamp
    private Date dateUpdate;

    private Long idDetailAgr;
    private Long ksu;

    @OneToOne(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn
    private Category category;

}

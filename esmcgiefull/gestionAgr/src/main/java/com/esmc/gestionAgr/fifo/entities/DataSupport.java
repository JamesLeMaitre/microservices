package com.esmc.gestionAgr.fifo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Table(name = "data_support")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class DataSupport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 2000000000)
    private String data;

    @ManyToOne
    @JoinColumn(name = "id_detail_support")
    private DetailSupport detailSupport;

    @ManyToOne
    @JoinColumn(name = "id_detail_type_support")
    private DetailTypeSupport detailTypeSupport;

    @CreationTimestamp
    private Date dateCreate;


    @UpdateTimestamp
    private Date dateUpdate;


}
package com.esmc.gestionAvr.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "data_support",indexes = @Index(name = "idx_",columnList = "id_detail_support,id_detail_type_support"))
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
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



  /*  @Temporal(TemporalType.TIMESTAMP)*/
    @Column(nullable = false)
    @CreationTimestamp
    private Date dateCreate;

/*    @Temporal(TemporalType.TIMESTAMP)*/
    @Column(nullable = false)
    @UpdateTimestamp
    private Date dateUpdate;


  /*  @PrePersist
    private void setDateTime() {
        dateCreate = dateUpdate = new Date();
    }

    @PreUpdate
    private void updateDateTime() {
        dateUpdate = new Date();
    }*/
}



package com.esmc.gestionAvr.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "middlewareextrant")
public class MiddleWareExtrant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idExtrant;
    private Boolean step;
    private Long intervenant;
    private Long posteEmetteur;
    private Long idDataSupport;

//    private boolean archiver = false;
//    private boolean vider = false;

    @CreationTimestamp
    private Date dateCreate;
    @UpdateTimestamp
    private Date dateUpdate;
}

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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @CreationTimestamp
    private Date dateCreate;
    @UpdateTimestamp
    private Date dateUpdate;
}
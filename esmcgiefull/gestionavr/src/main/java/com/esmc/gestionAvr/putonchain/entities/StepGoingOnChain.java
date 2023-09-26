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
@Table(name = "stepgoingonchains")
@NoArgsConstructor
@AllArgsConstructor
public class StepGoingOnChain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int step;
    private Long detailAgr;

    private boolean state ;

    private String data;
    private Long ksu;
    @CreationTimestamp
    private Date createDate;
    @UpdateTimestamp
    private Date updateDate;
}

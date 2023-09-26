package com.esmc.gestionAgr.fifo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FifoAdmin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numOrdre;
    private double userAmount = 0.0;
    private double currentAmountBCI = 0.0;
    private double basketAmountBCI = 0.0;
    private int tourTotal;

    private int  tourAlreadyTreated;

    private Date processingDate;
    @CreationTimestamp
    private Date dateCreate;
    @UpdateTimestamp
    private Date dateUpdate;


}

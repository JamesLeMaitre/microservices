package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * @author katoh
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailLivreBanque implements Serializable {

    private Long id;

    private Long numPc;

    private Double solde;

    private String natureOperation;

    private Double debit;

    private Double credit;

    private Date date;

    private Date dateCreate;

    private Date dateUpdate;

    private LivreBanque livreBanque;
}

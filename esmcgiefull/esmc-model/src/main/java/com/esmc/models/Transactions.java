package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transactions {

    private Long id;


    private String reference;

    private Double montant;


    private String data;


    private Long idTe;


    private Long idKsu;


    private String source;


    private String origin;

    private Boolean status =true;


    private Boolean used =false; //this attribute shows if the transaction has been used in the systemm


    private Date dateCreate;


    private Date dateUpdate;


}

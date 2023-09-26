package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionAdmins {

    private Long id;

    private String reference;

    private double montant;

    private String numero;

    private String prefix;

    private Long bon;

    private Long idDetailAgr;

    private Long typeFonds;

    private String source;

    private Long idTe;

    private Long idKsu;

    private int status;

    private Date dateCreate;

    private Date dateUpdate;

}

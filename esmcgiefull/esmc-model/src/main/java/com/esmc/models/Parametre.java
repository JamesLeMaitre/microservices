package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Parametre {

    private Long id;

    private String libelle;

    private Double PRK;

    private Double PCK ;

    private Double MontantCarte = 0.0 ;

    private Double MontantKsu = 0.0 ;

    private Double paramMABAn = 0.0;

    private Double paramMABAnZero = 0.0;

    private Date dateFinOpi;

    public  Double venteBCIMaxAmount = 385000000.0;

    public  Double achatBAnMaxAmount = 7000000.0;

    private Date dateCreate;

    private Date dateUpdate;
}

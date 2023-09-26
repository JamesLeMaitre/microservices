package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeMaBanKsuAgr implements Serializable {


    private Long id;


    private Long IdMaBanKsu;


    private Agr agr;


    private Date dateCreate;

    private Date dateUpdate;

}

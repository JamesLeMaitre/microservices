package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeCodes {

    private Long id;


    private String libelle;



    private Date dateCreate;


    private Date dateUpdate;

}

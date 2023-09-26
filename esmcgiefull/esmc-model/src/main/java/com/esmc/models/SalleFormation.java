package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalleFormation {

    private Long id;
    private String libelle;

    private TypeSalles typeSalles;

    private Date createCreate;

    private Date createUpdate;
}

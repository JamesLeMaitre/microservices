package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AffectionSalle {
    private Long id;
    private Long idAgr;

    private TypeSalles typeSalle;



    private SalleFormation salleFormation;

    private Long idDetailAgrFranchiser;
    private Long idPoste;
    private Long candidature;

    private Date dateCreate;
    private Date dateUpdate;
}

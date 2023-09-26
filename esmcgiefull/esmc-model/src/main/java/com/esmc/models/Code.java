package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Code {
    private Long id;
    private String code;
    private TypeCodes typeCode;
    private Long idAgr;
    private Long idAgrFranchiser;
    private AffectionSalle affectionSalle;
    private boolean etat;
    private boolean viewAdmin =false;
    private Date dateCreate;
    private Date dateUpdate;
}

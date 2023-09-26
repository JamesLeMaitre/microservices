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
public class SupportIntervenant {

    private Long id;

    private String libelle;

    private Long  idSupport;

    private Boolean status=false;

    private TypeSupport typeSupport;

    private Date dateCreate;

    private Date dateUpdate;

}

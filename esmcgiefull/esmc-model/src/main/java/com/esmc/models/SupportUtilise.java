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
public class SupportUtilise {

    private Long id ;

    private String libelle;

    private String code;

    private TableDescriptionEp tableDescriptionEp;

    private Date dateCreation;

    private Date dateUpdate;
}

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
public class Structure {

    private Long id;

    private String libelle;


    private Long niveau;

    private Boolean status;

    private Date dateCreate;

    private Date dateUpdate;


}

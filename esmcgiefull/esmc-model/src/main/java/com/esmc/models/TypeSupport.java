package com.esmc.models;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TypeSupport {

    private Long id;

    private String libelle;

    private String code;

    private List<PosteTypeSupport> typeSupports= new ArrayList<>();

    private Date dateCreate;

    private Date dateUpdate;

}

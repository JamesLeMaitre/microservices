package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PosteTypeSupport {

    private Long id;

    private Poste senderPoste;

    private Poste receiverPoste;

    private TypeSupport typeSupport;

    private TableDescriptionEp tableDescriptionEp;

    private boolean status;
}

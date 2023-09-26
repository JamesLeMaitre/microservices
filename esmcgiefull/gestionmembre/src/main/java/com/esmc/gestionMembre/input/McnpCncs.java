package com.esmc.gestionMembre.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class McnpCncs {

    @Column(name = "origine")
    private String origine;

    @Column(name = "montant")
    private Double montant;

}

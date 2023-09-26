package com.esmc.gestionAchatFranchise.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class jsonFil {
    @Column(nullable = false)
    private jsonInstitution[] data;
    private jsonFillCD[] cd;
}

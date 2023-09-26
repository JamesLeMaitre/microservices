package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EtapeFranchise {

    private Long id;

    private Long idKSU;

    private TypeFranchise typeFranchise;

    private Long etape;
}

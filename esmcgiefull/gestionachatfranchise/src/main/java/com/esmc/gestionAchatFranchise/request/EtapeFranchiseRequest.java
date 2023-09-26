package com.esmc.gestionAchatFranchise.request;

import com.esmc.gestionAchatFranchise.entities.TypeFranchise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtapeFranchiseRequest implements Serializable {
    private Long idKSU;
    private TypeFranchise typeFranchise;
    private Long etape;
}
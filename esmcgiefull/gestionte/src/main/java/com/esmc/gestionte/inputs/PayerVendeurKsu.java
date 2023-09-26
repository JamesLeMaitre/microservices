package com.esmc.gestionte.inputs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PayerVendeurKsu {

    private String codeBanKsu;

    private Long idDetailAgrFranchise;

}

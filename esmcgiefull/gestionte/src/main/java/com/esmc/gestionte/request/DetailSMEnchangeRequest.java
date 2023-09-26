package com.esmc.gestionte.request;

import lombok.Data;

@Data
public class DetailSMEnchangeRequest {
    private Long idTe;
    private Long idSmMuttan;
    private Long idSmMutter;
    private Double montant;
}

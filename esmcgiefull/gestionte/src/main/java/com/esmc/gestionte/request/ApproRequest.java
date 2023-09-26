package com.esmc.gestionte.request;

import lombok.Data;

@Data
public class ApproRequest {
    private Long  idTEAcheteur;
    private Long  idSMVendeur;
    private Long  idTEVendeur;
    private Long  idSMAcheteur;
    private double  montant;
}

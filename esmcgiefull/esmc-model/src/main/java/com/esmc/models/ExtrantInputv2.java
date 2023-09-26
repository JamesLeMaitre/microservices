package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtrantInputv2 {
    private Long idTypeSupport;
    private Long idTSupport;
    private String data;
    private Long posteEmetteur;
    private Long posteReceveur;
    private Long refer;
    private Long ksu;
    private Long[] posteReceveurOther;
    private Long detailAgrRecepteur;
    private Long detailAgrEmetteur;

    // Data Validation
    private Long idDetailAgr;
    private Long idPoste;
    private Boolean checkvalidFormation = false;
    private Long idDetailAgrFranchise;
}

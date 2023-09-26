package com.esmc.gestionAvr.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private String dataInfo;

}
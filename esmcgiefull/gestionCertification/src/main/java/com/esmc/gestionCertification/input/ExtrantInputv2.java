package com.esmc.gestionCertification.input;

import com.esmc.gestionCertification.entities.AppelCandidature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Long idCandidature;

    private  Long idposte;
    private Long  idDetailAgr;

    private Long idIntrant;
}

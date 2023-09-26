package com.esmc.gestionAvr.inputs;

import com.esmc.gestionAvr.entities.DataSupport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MInput {
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
    private Long idExtrant;
    private Long idIntevenant;
    private Long intervenantTour;

    private Long idDataSupport;

//    private DataSupport dataSupport;

}

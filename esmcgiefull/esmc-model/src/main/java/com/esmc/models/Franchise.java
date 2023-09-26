package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * @author katoh
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Franchise implements Serializable {

    private Long id;

    private String entetePage;

    private String piedPage;

    private String codeBan;

    private String codeBci;

    private String organisations;

    private Date dateCreate;

    private Date dateUpdate;

    private TypeFranchise typeFranchise;

    private DetailsAgr detailAgr;

    private CentreFranchise centreFranchise;
}

package com.esmc.gestionAchatFranchise.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class achatFranchiseInput {
    @Column(nullable = false)
    private Long idDetailAgr;

    @Column(nullable = false)
    private int stage;

    @Column(nullable = false)
    private Long idFranchise;

    @Column(nullable = true)
    private Long idFranchiseCanton;

    @Column(nullable = true)
    private Long idFranchiseCible;

    @Column(nullable = true)
    private Long idFranchiseIndicateur;

    @Column(nullable = true)
    private int cibleElement;

}

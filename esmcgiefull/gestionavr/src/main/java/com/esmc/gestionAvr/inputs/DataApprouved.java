package com.esmc.gestionAvr.inputs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataApprouved {
    private DataProuvedValue[] technopole;
    private DataProuvedValue[] filiere;
    private DataProuvedValue[] acnev;
    private DataProuvedValue[] operation;
    private DataProuvedValue[] service;
    private String step;
    private String canHaveOther;
}

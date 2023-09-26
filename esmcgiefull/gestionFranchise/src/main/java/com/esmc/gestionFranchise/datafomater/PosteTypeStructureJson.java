package com.esmc.gestionFranchise.datafomater;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PosteTypeStructureJson {
    private Long tdep;
    private Long emetteur;
    private Long recepteur;
    private Long [] supports;
}

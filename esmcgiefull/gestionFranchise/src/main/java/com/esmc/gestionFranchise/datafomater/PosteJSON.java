package com.esmc.gestionFranchise.datafomater;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PosteJSON {
    private Long structure;
    private String libelle;
    private PosteJSON [] children ;
}

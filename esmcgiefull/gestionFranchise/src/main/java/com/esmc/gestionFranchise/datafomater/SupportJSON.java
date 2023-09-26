package com.esmc.gestionFranchise.datafomater;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupportJSON {

    private Long id;
    private  UtilisateurJSON[] utilisateur;
}

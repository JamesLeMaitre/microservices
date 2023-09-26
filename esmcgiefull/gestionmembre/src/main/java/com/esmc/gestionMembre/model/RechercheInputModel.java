package com.esmc.gestionMembre.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;




@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RechercheInputModel {

    String codeMembreOrName="";
    String typeSysteme;
    String typePersonne;
    String nom="";
    String prenom="";
    String key;
}

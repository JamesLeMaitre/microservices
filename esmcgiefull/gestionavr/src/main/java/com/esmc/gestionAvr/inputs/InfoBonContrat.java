package com.esmc.gestionAvr.inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoBonContrat {
    private String prenom;
    private String nom;
    private String dateNaissance;
    private String nationalite;
    private String numOtrNif;
    private String nomPrenomOffreurOt;
    private String adresseOffreurOt;
    private String emailOffreurOt;

    private String numOtrNifTwo;
    private String dates;
    private String dateEmise;
    private TelephoneOffreurOt telephoneOffreurOt;
}

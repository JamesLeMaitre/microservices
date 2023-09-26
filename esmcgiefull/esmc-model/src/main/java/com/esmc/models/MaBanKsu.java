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
public class MaBanKsu implements Serializable {

    private Long id;

    private String nom;

    private String prenom;

    private String codeMembre;

    private String siteWeb;

    private String raisonSocial;

    private String codeKsuRepresentant;

    private String statusJuridique;

    private String denomination;

    private String telephone;

    private String adresse;

    private Long idCanton;

    private String justificationAdresse;

    private String boitePostale;

    private String email;

    private String photoJustificationAdresse;

    private Date dateCreate;

    private Date dateUpdate;

    private TypeMABanKSU typeMABanKSU;

    private FicheODD ficheODD;

}

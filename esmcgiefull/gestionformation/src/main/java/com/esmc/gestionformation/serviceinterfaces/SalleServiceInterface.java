package com.esmc.gestionformation.serviceinterfaces;

import com.esmc.gestionformation.entities.AffectationSalle;

import java.util.List;

public interface SalleServiceInterface {

    AffectationSalle addSalle(AffectationSalle s);
    public AffectationSalle updateSalle(Long id, AffectationSalle s);
    public void deleteSalle(Long id);
    public List<AffectationSalle> listSalle();
    public List<AffectationSalle> listSalleByType(Long idType);

    List<AffectationSalle> listByDetailsAgrFranchise(Long idDetailsAgrFranchise);

    public AffectationSalle getSalleByRaisonSociale(Long idSalleFormation);

    AffectationSalle getSalleById(Long id);
    List<AffectationSalle> getByIdPoste(Long idPoste);

    /* Registre Path */
    List<AffectationSalle> listFormationByKsu(Long idKSU);
}

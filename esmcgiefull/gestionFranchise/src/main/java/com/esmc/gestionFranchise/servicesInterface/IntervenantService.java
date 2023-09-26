package com.esmc.gestionFranchise.servicesInterface;

import com.esmc.gestionFranchise.entities.Intervenant;

import java.util.List;

public interface IntervenantService {
    List<Intervenant> getIntervenant();

    List<Intervenant> getIntervenantbytdep(Long id);

    int countBy(Long id);

    Intervenant ajouterIntervenant(Intervenant intervenant);

    Intervenant getIntervenantbyId(Long id);

    void deleteIntervenant(Long id);

    Intervenant update(Long id, Intervenant data);

    Intervenant getByPosteTdep(Long idPoste, Long idTdep);
}

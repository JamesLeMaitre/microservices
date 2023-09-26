package com.esmc.gestionAchatFranchise.repositories.affectation;

import com.esmc.gestionAchatFranchise.entities.felm.Affectation.IndicateurInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicateurInstitutionRepository extends JpaRepository<IndicateurInstitution, Long > {
}
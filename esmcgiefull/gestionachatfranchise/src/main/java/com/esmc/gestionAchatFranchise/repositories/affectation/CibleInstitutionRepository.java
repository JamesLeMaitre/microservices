package com.esmc.gestionAchatFranchise.repositories.affectation;

import com.esmc.gestionAchatFranchise.entities.felm.Affectation.CibleInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CibleInstitutionRepository extends JpaRepository<CibleInstitution, Long > {
}
package com.esmc.gestionAchatFranchise.repositories.felm.fill;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillInstitution;

public interface FillInstitutionRepository extends org.springframework.data.jpa.repository.JpaRepository<FillInstitution, Long> {
    FillInstitution getFillInstitutionById(Long id);
}

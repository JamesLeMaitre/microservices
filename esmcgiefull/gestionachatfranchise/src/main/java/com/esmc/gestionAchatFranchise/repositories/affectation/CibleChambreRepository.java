package com.esmc.gestionAchatFranchise.repositories.affectation;

import com.esmc.gestionAchatFranchise.entities.felm.Affectation.CibleChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CibleChambreRepository extends JpaRepository<CibleChambre, Long > {
}
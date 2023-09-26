package com.esmc.gestionAchatFranchise.repositories.affectation;

import com.esmc.gestionAchatFranchise.entities.felm.Affectation.IndicateurChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicateurChambreRepository extends JpaRepository<IndicateurChambre, Long > {
}
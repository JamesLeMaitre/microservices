package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.CentreFranchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentreFranchiseRepository extends JpaRepository<CentreFranchise, Long > {
}

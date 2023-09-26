package com.esmc.gestionPayement.Repositories;


import com.esmc.gestionPayement.entities.TraitementDemandeDebitInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraitementDemandeDebitInputRepo extends JpaRepository<TraitementDemandeDebitInput, Long> {
}

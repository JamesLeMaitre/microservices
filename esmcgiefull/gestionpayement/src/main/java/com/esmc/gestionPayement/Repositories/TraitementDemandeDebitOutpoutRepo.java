package com.esmc.gestionPayement.Repositories;


import com.esmc.gestionPayement.entities.TraitementDemandeDebitOutpout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraitementDemandeDebitOutpoutRepo extends JpaRepository<TraitementDemandeDebitOutpout, Long> {
}

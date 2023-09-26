package com.esmc.gestionPayement.Repositories;


import com.esmc.gestionPayement.entities.DebitServiceOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitServiceOutputRepo extends JpaRepository<DebitServiceOutput, Long> {
}

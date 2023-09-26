package com.esmc.gestionPayement.Repositories;


import com.esmc.gestionPayement.entities.DebitServiceInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitServiceInputRepo extends JpaRepository<DebitServiceInput, Long> {
}

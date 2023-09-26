package com.esmc.gestionPayement.Repositories;

import com.esmc.gestionPayement.entities.TypePayement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypePayementRepository extends JpaRepository<TypePayement, Long> {
}

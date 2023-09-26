package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.CompteMarchand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteMarchandRepository extends JpaRepository<CompteMarchand, Long> {
}

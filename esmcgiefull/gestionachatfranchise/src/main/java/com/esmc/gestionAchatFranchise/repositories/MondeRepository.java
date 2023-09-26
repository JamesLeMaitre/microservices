package com.esmc.gestionAchatFranchise.repositories;


import com.esmc.gestionAchatFranchise.entities.Monde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MondeRepository extends JpaRepository<Monde, Long> {
}

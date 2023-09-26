package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.EtapeFranchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtapeFranchiseRepository extends JpaRepository<EtapeFranchise, Long > {
    Optional<EtapeFranchise> findByIdKSU(Long id);
    @Query("select et from EtapeFranchise et where et.idKSU=:x and et.typeFranchise.id=:y")
    EtapeFranchise getByIdKsu(@Param("x")Long id,@Param("y")Long idy);
}
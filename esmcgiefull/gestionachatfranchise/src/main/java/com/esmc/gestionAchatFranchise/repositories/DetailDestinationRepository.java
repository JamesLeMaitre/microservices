package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.DetailDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailDestinationRepository extends JpaRepository<DetailDestination, Long> {
    @Query("select dd from DetailDestination dd where  dd.destination.id= :x")
    public List<DetailDestination> findDetailDestinationByDestination(@Param("x") Long id);
}

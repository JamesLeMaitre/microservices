package com.esmc.gestionAchatFranchise.repositories.franchise.referenciel;

import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import com.esmc.gestionAchatFranchise.entities.franchise.refereniel.FranchiseReferentiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FranchiseReferentielRepository extends JpaRepository<FranchiseReferentiel, Long> {
    @Query(value = "select a from FranchiseReferentiel a where a.id IN (:x) ")
    List<FranchiseReferentiel> findByIds( @Param("x")  List<Long> ids);

    @Query(value = "select a from FranchiseReferentiel a where a.id=:x ")
    List<FranchiseReferentiel> getByIdDetailAgr( @Param("x")  Long id);
}
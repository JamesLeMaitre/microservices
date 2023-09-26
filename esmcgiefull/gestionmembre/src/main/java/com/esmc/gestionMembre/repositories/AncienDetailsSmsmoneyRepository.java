package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.AncienDetailsSmsmoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AncienDetailsSmsmoneyRepository extends JpaRepository<AncienDetailsSmsmoney, Long> {
    @Query("SELECT a FROM AncienDetailsSmsmoney a WHERE a.codeMembreDist  = :x  AND a.origineSms = 'MF'")
    List<AncienDetailsSmsmoney> passifsMCNPmembreFondateurAncienDetailsSmsmoney (@Param("x") String code);


    @Query("SELECT a FROM AncienDetailsSmsmoney a WHERE trim(lower(a.codeMembre))= trim(lower(:x)) or trim(lower(a.codeMembreDist))= trim(lower(:x))  ")
    List<AncienDetailsSmsmoney> findByAncienDetailsSmsmoneyCodeMembre (@Param("x") String code);
}

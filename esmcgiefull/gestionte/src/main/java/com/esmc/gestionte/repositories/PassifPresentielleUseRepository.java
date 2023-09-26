package com.esmc.gestionte.repositories;

import com.esmc.gestionte.entities.PassifPresentielleUse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PassifPresentielleUseRepository extends JpaRepository<PassifPresentielleUse,Long> {
        @Query("select p from PassifPresentielleUse p where p.numero=:x and p.numContratAchat = :y  and p.numBonCommande=:z")
        PassifPresentielleUse findPassifPresentiellUseByInfo(@Param("x") String numero, @Param("y") String numContratAchat,  @Param("z") String numBonCommande);
    }
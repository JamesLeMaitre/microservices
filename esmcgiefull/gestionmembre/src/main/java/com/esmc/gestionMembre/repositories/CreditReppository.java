package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditReppository extends JpaRepository<Credit, Long> {
    @Query("SELECT a FROM Credit a WHERE a.membre = :x AND a.libelle = 'CNCS'")
    List<Credit> passifsRedemarreCncs(@Param("x") String code);




    @Query("SELECT a FROM Credit a WHERE a.membre = :x ")
    List<Credit> passifByCodeMemebre(@Param("x") String code);
}

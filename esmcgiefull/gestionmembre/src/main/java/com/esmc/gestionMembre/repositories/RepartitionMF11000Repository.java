package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.RepartitionMF11000;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepartitionMF11000Repository extends JpaRepository<RepartitionMF11000, Long>  {
    @Query("SELECT a FROM RepartitionMF11000 a WHERE a.codeMF11000 = :x")
    List<RepartitionMF11000> passifsMCNPmembreFondateurRepartitionMF11000(@Param("x") String code);



    @Query("SELECT a FROM RepartitionMF11000 a WHERE trim(lower(a.codeMembre)) =trim(lower(:x)) or  trim(lower(a.codeMF11000)) =trim(lower(:x)) ")
    List<RepartitionMF11000> getRepartitionMF11000ByCodeMembreOrName(@Param("x") String code);
}

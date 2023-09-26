package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    @Query("SELECT a FROM Place a WHERE a.membre = :x AND a.lib LIKE 'CNP%'")
    List<Place> passifsRedemarreCnp(@Param("x") String code);



    @Query("SELECT a FROM Place a WHERE trim(lower(a.membre)) = trim(lower(:x)) ")
    List<Place> passifRedemareByCodeMembre(@Param("x") String code);
}

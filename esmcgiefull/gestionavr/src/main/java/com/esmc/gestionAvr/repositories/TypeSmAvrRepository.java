package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.TypeSmAvr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeSmAvrRepository extends JpaRepository<TypeSmAvr, Long> {

    @Query("SELECT t from TypeSmAvr t where t.code = :x ")
    public TypeSmAvr DetailTypeBon(@Param("x") String code);
}

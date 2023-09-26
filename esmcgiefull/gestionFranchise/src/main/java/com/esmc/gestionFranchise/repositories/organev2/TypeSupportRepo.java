package com.esmc.gestionFranchise.repositories.organev2;

import com.esmc.gestionFranchise.entities.organev2.TypeSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TypeSupportRepo extends JpaRepository<TypeSupport,Long> {

    @Query("select t from TypeSupport t  ")
    List<TypeSupport> getAllType();

    @Query("select t.libelle from TypeSupport t where t.id=:x ")
    String getLibelle(@Param("x") Long id);
}

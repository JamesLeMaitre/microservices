package com.esmc.gestionAvr.repositories;


import com.esmc.gestionAvr.entities.TypeAchatEchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeAchatEchangeRepository extends JpaRepository<TypeAchatEchange, Long> {

    @Query("select t from TypeAchatEchange t where t.typeEchange.id = :x")
    public List<TypeAchatEchange> listTypeAchatEchangeByEchange(@Param("x") Long id);

}

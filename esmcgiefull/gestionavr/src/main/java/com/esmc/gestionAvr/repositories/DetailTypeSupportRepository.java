package com.esmc.gestionAvr.repositories;


import com.esmc.gestionAvr.entities.DetailTypeSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailTypeSupportRepository extends JpaRepository<DetailTypeSupport,Long> {
    @Query("select ds from DetailTypeSupport ds where ds.idTSupport =:x")
    public List<DetailTypeSupport> getAll(@Param("x")Long id);
}

package com.esmc.gestionformation.repositories;

import com.esmc.gestionformation.entities.SalleFormation;
import com.esmc.gestionformation.enums.SalleEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Enumerated;
import java.util.List;

public interface SalleFormationRepository extends JpaRepository<SalleFormation,Long> {
    @Query("select sf from SalleFormation sf where sf.typeSalles.id =:x")
    List<SalleFormation> findByTypesalle(@Param("x")Long id);


}

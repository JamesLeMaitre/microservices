package com.esmc.gestionformation.repositories;

import com.esmc.gestionformation.entities.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ValidationRepository extends JpaRepository<Validation,Long> {
    @Query("select v from Validation v where v.idAgr=:x and v.idDetailAgrFranchiser=:y and v.idPoste=:z")
    Validation getStatus(@Param("x")Long idx,@Param("y")Long idy,@Param("z")Long idz);
}

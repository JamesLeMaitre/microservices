package com.esmc.gestionCertification.repository;

import com.esmc.gestionCertification.entities.DataCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataCertificationRepository extends JpaRepository<DataCertification,Long> {

    @Query("select d from DataCertification d where d.panierCertification.id=:x")
    DataCertification getDataCertification(@Param("x") Long id);
}

package com.esmc.gestionte.repositories;

import com.esmc.gestionte.entities.PassifPresentielle;
import com.esmc.gestionte.entities.PassifPresentielleTreat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassifPresentielleTreatRepository extends JpaRepository<PassifPresentielleTreat,Long> {

    @Query("select p from PassifPresentielleTreat p where p.ksuId=:x and p.newCode = :y  and p.enableUseStatus=true")
    PassifPresentielleTreat findPassifPresentiellTreatByKsuAndCode(@Param("x") Long idKsu, @Param("y") String code);

    PassifPresentielleTreat getPassifPresentielleTreatById(Long id);

    @Query("select p from PassifPresentielleTreat p where p.ksuId = :x")
    PassifPresentielleTreat getPassifPresentielleTreatByKsuId(@Param("x") Long idKsu);
}

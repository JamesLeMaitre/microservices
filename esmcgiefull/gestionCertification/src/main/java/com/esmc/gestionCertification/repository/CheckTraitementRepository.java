package com.esmc.gestionCertification.repository;

import com.esmc.gestionCertification.entities.CheckTraitement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CheckTraitementRepository extends JpaRepository<CheckTraitement,Long> {
    @Query("select ck from CheckTraitement ck where ck.idDetailAgrFranchiser=:x and ck.idDetailAgr=:y and ck.candidature=:z and ck.idPoste=:t")
    CheckTraitement findCheckTraitementByCandidature(@Param("x")Long idx,@Param("y")Long idy,@Param("z")Long idz,@Param("t")Long idt);
}

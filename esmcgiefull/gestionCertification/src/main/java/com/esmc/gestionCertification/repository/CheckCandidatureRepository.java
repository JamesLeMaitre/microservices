package com.esmc.gestionCertification.repository;

import com.esmc.gestionCertification.entities.CheckCandidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckCandidatureRepository extends JpaRepository<CheckCandidature,Long> {

    @Query("select ch from CheckCandidature ch where ch.idPoste =:x and ch.checkstatus = false")
    List<CheckCandidature> verifIfExist(@Param("x")Long id);

//    @Query("select af from CheckCandidature af where  af.idposteEmetteur not in (:x)")
//    public List<CheckCandidature> getWithoutSaved(@Param("x") Long id);
//
//    @Query("select af from CheckCandidature af where  af.idposteEmetteur=:x")
//    public List<CheckCandidature> checkBy(@Param("x") Long id);
//
//    @Query("select count(af) from CheckCandidature af where  af.idposteEmetteur=:x")
//    int countCheck(@Param("x") Long id);
//
//
//
//    @Query("select af from CheckCandidature af where  af.idposteEmetteur not in (:x)")
//    public List<CheckCandidature> get(@Param("x") Long id);

}

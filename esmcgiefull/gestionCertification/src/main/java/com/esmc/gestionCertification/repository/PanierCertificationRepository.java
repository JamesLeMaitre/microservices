package com.esmc.gestionCertification.repository;

import com.esmc.gestionCertification.entities.PanierCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PanierCertificationRepository extends JpaRepository<PanierCertification, Long> {

    @Query("select p from PanierCertification p where  p.idDetailAgr = :x and p.idPoste =:y")
    public List<PanierCertification> listPanierCertByDetailAgrAndPoste(@Param("x") Long idDetailAgr, @Param("y") Long idPoste);

    @Query("select p from PanierCertification p where  p.idDetailAgr = :x and p.idPoste =:y and p.idDetailAgrFranchiser=:z")
    public PanierCertification getPanierCertByDetailAgrAndPoste(@Param("x") Long idDetailAgr, @Param("y") Long idPoste,@Param("z") Long idz);
}

package com.esmc.gestionCertification.repository;

import com.esmc.gestionCertification.entities.Chargement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChargementRepository extends JpaRepository<Chargement,Long> {


    @Query("select ch from Chargement ch where  ch.typeChargement.id= :x")
    public List<Chargement> findChargementBytypeChargement(@Param("x") Long id);


    @Query("select ch from Chargement ch where ch.candidature.id= :x")
    public List<Chargement> findChargementBycandidature(@Param("x") Long id);

    @Query("select ch from Chargement ch where ch.detailAgr= :x")
    public Chargement findChargementByDetailAgr(@Param("x") Long id);

    @Query("select ch from Chargement ch where  ch.idPoste = :x")
    public List<Chargement> findChargementByPoste(@Param("x") Long id);

    @Query("select ch from Chargement ch where  ch.idPoste = :x and ch.detailAgr=:y")
    public Chargement findChargementByPosteDAndDetailAgr(@Param("x") Long x,@Param("y") Long id);

    @Query("select ch from Chargement ch where  ch.detailAgr=:x and ch.idDetailAgrFranchiser=:y and ch.candidature.id=:z  and ch.idPoste is null ")
    public Chargement findChargementByPosteDAndDetailAgrNoPoste(@Param("x") Long x,@Param("y") Long id,@Param("z") Long idz);

    @Query("select ch from Chargement ch where  ch.detailAgr=:x and ch.typeChargement.id=:y and ch.candidature.id=:z")
    public Chargement findChargementByPosteDAndDetailAgrNoPostev1(@Param("x") Long x,@Param("y") Long id,@Param("z") Long idz);

    //@Query("select ch from Chargement ch where  ch.typeChargement.id= :x and ch.detailAgr = :Y")
   // @Query(value = "SELECT libelle FROM typemabanksu t, ban_ksu b, ma_ban_ksu mb WHERE b.id_ma_ban_ksu  = mb.id AND mb.id_type_mabanksu = t.id  and trim(lower(b.code_ban_ksu)) =:x ",nativeQuery = true)

    //@Query(value = "SELECT * FROM chargement ch WHERE id_detail_agr=:x and type_chargement_id =:y ",nativeQuery = true)
    @Query(value = "SELECT c  FROM Chargement c WHERE c.detailAgr=:x and c.typeChargement.id =:y ")
    public List<Chargement> findChargementByTypeChargementAndDetailAgr(@Param("x") Long idx, @Param("y") Long idy);

    @Query(value = "SELECT c  FROM Chargement c WHERE c.detailAgr=:x and c.candidature.id =:y ")
    public Chargement getChargementByTypeChargementAndDetailAgr(@Param("x") Long idx, @Param("y") Long idy);

    @Query(value = "SELECT c  FROM Chargement c WHERE c.detailAgr=:x and c.idPoste =:y ")
    public Chargement getChargementByDetailAgrAndPoste(@Param("x") Long idx, @Param("y") Long idy);

    @Query(value = "SELECT c  FROM Chargement c WHERE c.detailAgr=:x")
    public List<Chargement> listChargementByIdDetailAgr(@Param("x") Long idDetailAgr);

    @Query(value = "SELECT c  FROM Chargement c WHERE c.detailAgr=:x and c.candidature.id =:y and c.idPoste != null")
    public List<Chargement> listChargementByIdDetailAgrAndCanditure(@Param("x") Long idx, @Param("y") Long idy);


}

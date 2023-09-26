package com.esmc.demandeAchatBanKsu.repositories;

import com.esmc.demandeAchatBanKsu.entities.MaBanKsu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
    public interface MaBanKsuRepository extends JpaRepository<MaBanKsu,Long> {


   /* @Query(value = "select * from ma_ban_ksu where typemabanksu_id=:x",nativeQuery = true)
    public String findMaBanKSUByTypeMaBanKSu(@Param("x") String type);*/

    @Query("select m from MaBanKsu m where m.typeMABanKSU.id = :x")
    public MaBanKsu findMaBanKSUByTypeMaBanKSu(@Param("x") Long id);

    @Query("select m from MaBanKsu m where m.codeKsuRepresentant = :x")
    public MaBanKsu findMaBanKsuByCodeKsuRepresentant(@Param("x") String code);

/*    @Query("select m from MaBanKsu m")
    MaBanKsu findByKsuId(@Param("x") Long id);*/

    @Query("select max(m) from MaBanKsu m")
    public MaBanKsu findMaBanKsuByIdMax();

    @Query("select m from MaBanKsu m where m.idCanton = :x")
    public List<MaBanKsu> findMaBanByCanton(@Param("x") Long id);
}

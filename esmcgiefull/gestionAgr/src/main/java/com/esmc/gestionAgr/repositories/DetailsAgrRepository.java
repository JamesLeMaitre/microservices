package com.esmc.gestionAgr.repositories;

import com.esmc.gestionAgr.entities.DetailsAgr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailsAgrRepository extends JpaRepository<DetailsAgr,Long> {


    @Query(value = "INSERT INTO detail_agr (id_agr) SELECT  FROM agr where id=1",nativeQuery = true)
    public String insere();

    @Query("select d from DetailsAgr d where d.ksu =:x")
    public List<DetailsAgr> findDetailAgrByIdkSU(@Param("x") Long idKSU);

    @Query("select d from DetailsAgr d WHERE d.typeMaBanKsuAgr.id =:x")
    public DetailsAgr findDetailsAgrByIdKsu(@Param("x") Long id);





}

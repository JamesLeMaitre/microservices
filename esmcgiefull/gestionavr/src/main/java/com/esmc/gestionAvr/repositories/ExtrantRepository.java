package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.Extrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtrantRepository  extends JpaRepository<Extrant, Long> {

    @Query("SELECT a FROM Extrant a WHERE a.id = :x and a.archive = true and a.viewDev=false ")
     Extrant getExtrantArchive(@Param("x") Long id);

    @Query("SELECT a FROM Extrant a WHERE a.id = :x and a.archive = false and a.viewDev=false ")
    Extrant getExtrantByNoArchiveV2(@Param("x") Long id);

    @Query("SELECT a FROM Extrant a WHERE a.avr.id = :x")
     List<Extrant> recuperatiionExtrantAvr(@Param("x") Long id);

    @Query("SELECT a FROM Extrant a where a.viewDev=false ORDER BY a.id desc")
     List<Extrant> getAllV2();

    @Query("SELECT a FROM Extrant a WHERE a.ksuEmetteur = :x or a.ksuRecepteur = :x")
     List<Extrant> ExtrantByKsu(@Param("x") Long id);

    @Query("SELECT a FROM Extrant a WHERE a.avr.id = :x ")
    List<Extrant> getByIdAvr(@Param("x") Long id);

    @Query("SELECT a FROM Extrant a WHERE a.detailAgrEmetteur = :x ")
    List<Extrant> getByIdDetailAgr(@Param("x") Long id);

    @Query("SELECT a FROM Extrant a WHERE a.id = :x")
    Extrant getByPosteEmetteur(@Param("x") Long id);

    @Query("SELECT a FROM Extrant a WHERE a.posteEmetteur = :x and a.supportEtablie=false")
    List<Extrant> getListByPosteEmetteur(@Param("x") Long id);

    @Query("SELECT a FROM Extrant a WHERE a.posteEmetteur = :x and a.supportEtablie=true")
    List<Extrant> getListByPosteEmetteurEtablie(@Param("x") Long id);

    @Query("SELECT a FROM Extrant a WHERE a.detailSupport.id = :x")
    List<Extrant> getExtrantByDetailsSupport(@Param("x") Long id);

    @Query("SELECT a FROM Extrant a WHERE a.id= :x")
    Extrant archiver(@Param("x") Long id);

    @Query("SELECT a FROM Extrant a WHERE a.posteEmetteur = :x and a.archive=true")
    List<Extrant> listArchiver(@Param("x") Long id);

    // EXTRANT 16/12/2022 by DetailAgr

    @Query("SELECT a FROM Extrant a WHERE a.detailAgrEmetteur= :x and a.archive=false")
    List<Extrant> getExtrantByDetailAgrFalse(@Param("x") Long id);

    @Query("SELECT a FROM Extrant a WHERE a.detailAgrEmetteur= :x and a.archive=true")
    List<Extrant> getExtrantByDetailAgrTrue(@Param("x") Long id);

    @Query("SELECT count (a.id) FROM Extrant a WHERE a.detailAgrEmetteur= :x and a.archive=true")
    int countTrue(@Param("x") Long id);

    @Query("SELECT a FROM Extrant a WHERE a.id= :x and a.archive=false")
    Extrant getNoArchiver(@Param("x") Long id);


//    @Query("select a from Extrant a where a.affectationSMAvr.id = :x")
//    public List<Extrant> ExtrantByAffSMAvr(@Param("x") Long id);
}


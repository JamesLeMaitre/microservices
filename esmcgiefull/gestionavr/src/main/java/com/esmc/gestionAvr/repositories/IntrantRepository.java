package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.Extrant;
import com.esmc.gestionAvr.entities.Intrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntrantRepository extends JpaRepository<Intrant, Long> {
    @Query("SELECT a FROM Intrant a WHERE a.avr.id = :x")
    public List<Intrant> recuperationIntrantAvr(@Param("x") Long id);

    @Query("SELECT a FROM Intrant a WHERE a.ksuRecepteur = :x")
    public List<Intrant> IntrantByKsu(@Param("x") Long id);

    @Query("SELECT a FROM Intrant a WHERE a.avr.id = :x")
    List<Intrant> getByIdAvr(@Param("x") Long id);

    @Query("SELECT a FROM Intrant a WHERE  a.detailAgrRecepteur = :x")
    List<Intrant> getByIdDetailAgr(@Param("x") Long id);

    @Query("SELECT a FROM Intrant a WHERE  a.id = :x")
    Intrant getByPosteReceveur(@Param("x") Long id);

    @Query("SELECT a FROM Intrant a WHERE  a.posteReceveur = :x and a.supportEtablie=false ")
    List<Intrant> getListByPosteReceveur(@Param("x") Long id);


    @Query("SELECT a FROM Intrant a WHERE  a.detailSupport.id = :x")
    List<Intrant> getIntrantByDetailsSupport(@Param("x") Long id);

    @Query("SELECT a FROM Intrant a WHERE  a.posteReceveur = :x and a.supportEtablie=true  ")
    List<Intrant> getSupportEtablie(@Param("x") Long id);

    @Query("SELECT a FROM Intrant a WHERE  a.posteReceveur = :x and  a.id= :x")
    Intrant archiver(@Param("x") Long id);

    @Query("SELECT a FROM Intrant a WHERE  a.posteReceveur = :x and  a.archive=true")
    List<Intrant> listArchiver(@Param("x") Long id);


    /// Intrant by DetailAgr 16/12/2022
    @Query("SELECT a FROM Intrant a WHERE  a.detailAgrRecepteur = :x and  a.archive=false")
    List<Intrant> getIntrantByDetailAgrFalse(@Param("x") Long id);

    @Query("SELECT a FROM Intrant a WHERE  a.detailAgrRecepteur = :x and  a.archive=true ")
    List<Intrant> getIntrantByDetailAgrTrue(@Param("x") Long id);

    @Query("SELECT a FROM Intrant a WHERE  a.detailTypeSupport.id = :x and  a.viewDev=false ")
    List<Intrant> getIntrantByDetailTypeSupport(@Param("x") Long id);



//    @Query("select a from Intrant a where a.affectationSMAvr.id = :x")
//    public List<Intrant> intrantByAffSMAvr(@Param("x") Long id);
}

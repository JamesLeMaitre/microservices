package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.Avr;
import com.esmc.gestionAvr.entities.DetailSMAvr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailSMAvrRepository extends JpaRepository<DetailSMAvr, Long> {

    @Query("select d from DetailSMAvr d where d.smAvr.id= :x")
    public List<DetailSMAvr> DetailBySmavr(@Param("x") Long id);

    @Query("select d from DetailSMAvr d where d.avr.id= :x")
    public List<DetailSMAvr> DetailByAvr(@Param("x") Long id);

    @Query("select d from DetailSMAvr d where d.avr = :x")
    public List<DetailSMAvr> DetailByAvrOfFifoAcheteur(@Param("x") Avr avr);

    @Query("select d from DetailSMAvr d where d.avr= :x")
    public List<DetailSMAvr> DetailByAvrOfFifoVendeur(@Param("x") Avr avr);
}

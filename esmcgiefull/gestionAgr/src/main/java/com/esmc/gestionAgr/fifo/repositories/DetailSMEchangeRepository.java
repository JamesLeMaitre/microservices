package com.esmc.gestionAgr.fifo.repositories;


import com.esmc.gestionAgr.fifo.entities.DetailSMEnchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DetailSMEchangeRepository extends JpaRepository<DetailSMEnchange, Long> {
    @Query("select coalesce(sum(d.fondsFondDisponible),0) from DetailSMEnchange d where d.supportMarchandEnchage.id = :x and d.terminalEchange.id = :y and d.fondsFondDisponible <> 0")
    Double sommeTotalSm(@Param("x") Long id, @Param("y") Long idTe);

    @Query("select d from DetailSMEnchange d where d.supportMarchandEnchage.id = :x and d.terminalEchange.id = :y and d.fondsFondDisponible > 0 and d.used = true")
    List<DetailSMEnchange> listdSMFondsDisponibleUsed(@Param("x") Long idSm, @Param("y") Long idTe);

    @Query("SELECT a FROM DetailSMEnchange a WHERE trim(lower(a.codeSM)) =trim(lower(?1)) ")
    Optional<DetailSMEnchange> findDetailSMEnchangeByCodeSM(String codeSM);

    @Query(value = "select d from DetailSMEnchange d where d.id = :x and d.terminalEchange.id = :y")
    DetailSMEnchange detailSMEchangeByIdTe(@Param("x") Long id, @Param("y") Long idTe);

    @Query("select d from DetailSMEnchange d where d.supportMarchandEnchage.id = :x and d.terminalEchange.id = :y and d.fondsFondDisponible > 0")
    List<DetailSMEnchange> listdSMFondsDisponible(@Param("x") Long idSm, @Param("y") Long idTe);


}

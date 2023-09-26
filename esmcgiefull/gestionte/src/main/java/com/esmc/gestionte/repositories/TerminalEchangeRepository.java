package com.esmc.gestionte.repositories;

import com.esmc.gestionte.entities.TerminalEchange;
import com.esmc.models.TypeMaBanKsuAgr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

;import java.util.List;

/**
 * @author Amemorte
 * @since 05/05/2022
 */


@Repository
public interface TerminalEchangeRepository extends JpaRepository<TerminalEchange,Long> {


//    @Query(value = "SELECT * FROM `terminal_echange` WHERE`id_detail_agr`=:x ",nativeQuery = true)
//    public TerminalEchange findByDetailAgr(@Param("x") Long id);

    @Query("select t from TerminalEchange t where t.detailAgr = :x")
    public TerminalEchange findByDetailAgr(@Param("x") Long id);
    @Query(value = "SELECT tmbka.* from type_ma_ban_ksu_agr tmbka, details_agr da, terminal_echange te where  te.id_detail_agr = da.id and da.id_type_ma_ban_ksu_agr = tmbka.id and te.id=:x", nativeQuery = true)
    List<TypeMaBanKsuAgr> findByTypeMaBanKsuAgr(@Param("x") Long id);
}

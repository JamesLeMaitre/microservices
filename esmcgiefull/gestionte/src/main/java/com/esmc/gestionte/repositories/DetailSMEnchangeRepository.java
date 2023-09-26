package com.esmc.gestionte.repositories;


import com.esmc.gestionte.entities.DetailSMEnchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * @author Amemorte
 * @since 05/05/2022
 */




@Repository
public interface DetailSMEnchangeRepository  extends JpaRepository<DetailSMEnchange,Long> {

    @Query("SELECT a FROM DetailSMEnchange a WHERE trim(lower(a.codeSM)) =trim(lower(?1)) ")
    Optional<DetailSMEnchange> findDetailSMEnchangeByCodeSM(String codeSM);

    @Query(value = "Select d from DetailSMEnchange d where d.id = :x and d.terminalEchange.id = :y")
    DetailSMEnchange detailSMEchangeByIdTe(@Param("x") Long id, @Param("y") Long idTe);

    @Query("select a from DetailSMEnchange a where a.supportMarchandEnchage.id = :x and a.terminalEchange.id = :y")
    List<DetailSMEnchange> listDetailSmByIdSmAndIdTe(@Param("x") Long idSm, @Param("y") Long idTe);


    @Query("select a from DetailSMEnchange a where a.supportMarchandEnchage.id = :x")
    public List<DetailSMEnchange> listDSMSUM(@Param("x") Long id);

    @Query("select a from DetailSMEnchange a where a.supportMarchandEnchage.id = :y and a.terminalEchange.id=:x")
    public List<DetailSMEnchange> listDSMEByIdTeAndSM(@Param("x") Long id,@Param("y") Long support);

    @Query("select a from DetailSMEnchange a where a.terminalEchange.id = :x")
    public List<DetailSMEnchange> listDecaissemntByTe(@Param("x") Long id);

    @Query(value = "select a.* from detailsmenchange a where a.id_support_marchand_enchage=:x and  a.id_terminal_echange in ( select te.id from terminal_echange te where te.id_detail_agr in (select te1.id_detail_agr from terminal_echange te1 WHERE id=:y)); ", nativeQuery = true)
    public List<DetailSMEnchange> listDecaissemntByKsu(@Param("x") Long idSupportMarchand, @Param("y") Long idTe);


    @Query("select d from DetailSMEnchange d where d.terminalEchange.id = :x")
    public List<DetailSMEnchange> listDeatailSme(@Param("x") Long id);

    @Query("select d from DetailSMEnchange d where d.terminalEchange.id = :x and d.supportMarchandEnchage.libelle = 'BCi BLGCp' and d.fondsFondDisponible != 0 and d.used = false")
    public List<DetailSMEnchange> listBciReinitialiser(@Param("x") Long id);

    @Query("select SUM(d.fondsFondDisponible) from DetailSMEnchange d where d.terminalEchange.id = :x and d.supportMarchandEnchage.id =:y and d.fondsFondDisponible != 0 and d.used = false")
    public Double sommeTotalDSMByTeAndSMDE(@Param("x") Long idTe, @Param("y") Long idSM);

    @Query("select d from DetailSMEnchange d where d.terminalEchange.id = :x and d.supportMarchandEnchage.id = 5 and d.status=true ")
    List<DetailSMEnchange> listDetailSMEnchangeByTeAndSupportExchange(@Param("x") Long idTe);

    @Query("Update DetailSMEnchange d  set d.status=false where d.id=?1 ")
    void changeSelectDetailSMEnchange(Long id);

    @Query("select sum(a.total) from DetailSMEnchange a where a.terminalEchange.id = :x")
    double TotalListDecaissemntByTe(@Param("x") Long id);

    @Query(value = "select COALESCE(sum(a.total),0) from detailsmenchange a where a.id_support_marchand_enchage=:x and  a.id_terminal_echange in ( select te.id from terminal_echange te where te.id_detail_agr in (select te1.id_detail_agr from terminal_echange te1 WHERE id=:y))", nativeQuery = true)
    Double cumuleAmountByKsu(@Param("x") Long idSupportMarchand, @Param("y") Long idTe);

    @Query("select d from DetailSMEnchange d where d.supportMarchandEnchage.id = :x ")
    DetailSMEnchange findBySmEchange(@Param("x") Long id);

    @Query("select coalesce(sum(d.total),0) from DetailSMEnchange d where d.supportMarchandEnchage.id = :x and d.terminalEchange.id = :y")
    Double sommeTotalSmBan(@Param("x") Long id, @Param("y") Long idTe);

    @Query("select coalesce(sum(d.fondsFondDisponible),0) from DetailSMEnchange d where d.supportMarchandEnchage.id = :x and d.terminalEchange.id = :y and d.fondsFondDisponible != 0")
    Double sommeTotalSm(@Param("x") Long id, @Param("y") Long idTe);

    @Query("select coalesce(sum(d.total),0) from DetailSMEnchange d where d.supportMarchandEnchage.id = :x and d.terminalEchange.id = :y and d.used = false")
    Double sommeTotalSmBci(@Param("x") Long id, @Param("y") Long idTe);

    @Query("select d from DetailSMEnchange d where d.supportMarchandEnchage.id = :x and d.terminalEchange.id = :y and d.fondsFondDisponible > 0")
    List<DetailSMEnchange> listdSMFondsDisponible(@Param("x") Long idSm, @Param("y") Long idTe);

    @Query("select coalesce(sum(d.fondsFondDisponible),0) from DetailSMEnchange d where d.supportMarchandEnchage.id = :x and d.terminalEchange.id = :y and d.fondsFondDisponible != 0")
    Double sommeTotalSmProduit(@Param("x") Long id, @Param("y") Long idTe);

    @Query("select d from DetailSMEnchange d where d.supportMarchandEnchage.id = :x and d.terminalEchange.id = :y and d.fondsFondDisponible > 0 and d.used = true")
    List<DetailSMEnchange> listdSMFondsDisponibleUsed(@Param("x") Long idSm, @Param("y") Long idTe);

    @Query("select d from DetailSMEnchange d where d.supportMarchandEnchage.id = :x and d.terminalEchange.id = :y")
    List<DetailSMEnchange> listdSM(@Param("x") Long idSm, @Param("y") Long idTe);

   // @Query("Select d from DetailSMEnchange d where d.terminalEchange.id = :x and d.supportMarchandEnchage.id = 61 or d.supportMarchandEnchage.id = 62 or d.supportMarchandEnchage.id = 70 or d.supportMarchandEnchage.id = 71")
   @Query(value = "SELECT * FROM detailsmenchange WHERE id_terminal_echange = :x AND id_support_marchand_enchage IN  (61, 62, 70, 71)", nativeQuery = true)
    List<DetailSMEnchange> listBPSD(@Param("x") Long idTe);

    @Query("Select d from DetailSMEnchange d where d.terminalEchange.id = :idTE and d.supportMarchandEnchage.id = :idSm")
    List<DetailSMEnchange> listSmMuter(@Param("idTE") Long idTe,@Param("idSm") Long idSm);

    @Query("Select d from DetailSMEnchange d where d.refer = :x  and d.terminalEchange.id = 7")
    DetailSMEnchange getSmFromInterim(@Param("x") Long refer);

}

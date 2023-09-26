package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.RegistreCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistreCommandeRepository extends JpaRepository<RegistreCommande, Long> {

    @Query("SELECT a FROM RegistreCommande a WHERE a.detailSMAvr.id = :x")
    public List<RegistreCommande> ListRegistreCommandByDetailSMAvrId(@Param("x")  Long id);




    /*@Query("SELECT a FROM RegistreCommande r WHERE r.detailSMAvr.numOrdre = :x ")
    List<RegistreCommande> ListRegistreCommandByDetailSMAvrnumOrdre(@Param("x") String numOrdre);*/

    @Query("SELECT r FROM RegistreCommande r WHERE r.detailSMAvr.reference = :x ")
    public List<RegistreCommande> ListRegistreCommandByDetailSMAvrreference(@Param("x") String reference);

    @Query("SELECT a FROM RegistreCommande a  WHERE a.detailSMAvr.id = :x AND a.detailSMAvr.reference = :y")
    public List<RegistreCommande> listRegistreCommandeByDetailSMAvrIdAndType(@Param("x") Long id, @Param("y") String reference);

    /*@Query("SELECT a FROM RegistreCommande r WHERE r.detailSMAvr.article = :x ")
    List<RegistreCommande> ListRegistreCommandByDetailSMAvrarticle(@Param("x") String article);*/


    /*@Query("SELECT a FROM RegistreCommande r WHERE r.detailSMAvr.quantite = :x ")
    List<RegistreCommande> ListRegistreCommandByDetailSMAvrquantite(@Param("x") int quantite);*/


    /*@Query("SELECT a FROM RegistreCommande r WHERE r.detailSMAvr.prixUnitaire = :x ")
    List<RegistreCommande> ListRegistreCommandByDetailSMAvrprixUnitaire(@Param("x") Double prixUnitaire);*/

    /*@Query("SELECT a FROM RegistreCommande r WHERE r.detailSMAvr.valeur = :x ")
    List<RegistreCommande> ListRegistreCommandByDetailSMAvrvaleur(@Param("x") Double valeur);*/
}

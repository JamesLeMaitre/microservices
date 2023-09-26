package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.Fifo;
import com.esmc.gestionAvr.utils.enums.KsuType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FifoRepository extends JpaRepository<Fifo, Long> {
    @Query("SELECT f FROM Fifo f WHERE f.detailAgr = :x ")
    public List<Fifo> ListFifoByDetailAgrId(@Param("x") Long id);


    /*@Query("SELECT f from Fifo f WHERE f.avr.id = :x ORDER BY f.numOrdre")
    public List<Fifo> ListFifoByAvr(@Param("x") Long id);*/

                        
    /*@Query("select f from Fifo f where f.avr.typeAvr.libelle = :x and f.avr.etat = true ORDER BY f.numOrdre ")
    public List<Fifo> ListFifoByTypeAvr1(@Param("x") String libelle);*/

    @Query("select f from Fifo f where f.avr.typeAvr.libelle like '%vente%' and f.avr.etat = false ORDER BY f.numOrdreVente")
    public List<Fifo> ListFifoByTypeAvrVente();

    @Query("select f from Fifo f where f.avr.etat = true")
    public List<Fifo> ListFifoActif();

    @Query("select f from Fifo f where f.avr.etat = false")
    public List<Fifo> ListFifoInactif();

    @Query("select f from Fifo f where f.avr.etat = false and f.avr.typeAvr.libelle like '%vente%' ORDER BY f.numOrdreVente")
    public List<Fifo> ListFifoVenteInactif();

    @Query("select f from Fifo f where f.avr.etat = false and f.avr.typeAvr.libelle like '%achat%' ORDER BY f.numOrdreAchat")
    public List<Fifo> ListFifoAchatInactif();

    @Query("select f from Fifo f where f.avr.etat = true and f.avr.typeAvr.libelle like '%vente%' ORDER BY f.numOrdreVente")
    public List<Fifo> ListFifoVenteActif();

    @Query("select f from Fifo f where f.avr.etat = true and f.avr.typeAvr.libelle like '%achat%' ORDER BY f.numOrdreAchat")
    public List<Fifo> ListFifoAchatActif();



    @Query("select f from Fifo f where f.avr.libelle =:x and f.avr.typeAvr.libelle like '%achat%' and f.avr.etat = true ")
    public List<Fifo> ListAchats(@Param("x")String libelle);

    @Query("select f from Fifo f where f.avr.libelle =:x and f.avr.typeAvr.libelle like '%vente' and f.avr.etat = true")
    public List<Fifo> ListVente(@Param("x")String libelle);

    @Query("select f from Fifo f where f.avr.typeAvr.libelle like '%achat%' and f.avr.etat = false ORDER BY f.numOrdreAchat")
    public List<Fifo> ListFifoByTypeAvrAchat();

    @Query("select f from Fifo f where f.avr.typeAvr.libelle like '%vente%' and f.numOrdreVente = min(f.numOrdreVente) and f.avr.etat = true")
    public Fifo premierVente();

    @Query("select f from Fifo f where f.avr.typeAvr.libelle = 'achat' and f.numOrdreAchat = min(f.numOrdreAchat) and f.avr.etat = true")
    public Fifo premierAchat();

    @Query("select f.avr.prixUnitaire*f.avr.quantite as montant from Fifo f where f.avr.typeAvr.libelle = 'vente' and f.numOrdreVente = min(f.numOrdreVente) and f.avr.etat = true ")
    public double PrixQuantiteVente();

    @Query("select f.avr.prixUnitaire*f.avr.quantite as montant from Fifo f where f.avr.typeAvr.libelle = 'achat' and f.numOrdreAchat = min(f.numOrdreAchat) and f.avr.etat = true")
    public double PrixQuantiteAchat();

    @Query("select count(f) from Fifo f where f.ksuType=0 and f.isTreated=false and f.panierTour=false")
    public int dernierFifoVente();

    @Query("select count(f) from Fifo f where f.ksuType=1 and f.isTreated=false and f.panierTour=false")
    public int dernierFifoAchat();


//    public int countByPanierTourIsFalseAndKsuTypeIsFalseAndIsAndTreatedIsFalse();

//    @Query("select count(f) from Fifo f where  f.ksuType=0 and f.isTreated=false and f.panierTour=false ")
//    public int positionVEnte();
//
//    @Query("select count(f) from Fifo f where  f.ksuType=1 and f.isTreated=false and f.panierTour=false ")
//    public int positionAchat();

//    @Query("select f from Fifo f where f.detailAgr.ksu.id = :x")
//    public List<Fifo> listFifoByKsu(@Param("x") Long id);

    @Query("select SUM (f.avr.prixUnitaire) from Fifo f where f.avr.typeAvr.libelle ='vente' and f.avr.etat = true")
    public Double sommeAchat();


    /*@Query("SELECT f FROM Fifo f WHERE f.ficheODD.id = :x ORDER BY f.dateCreate")
    public List<Fifo> ListFifoByFicheODDId(@Param("x") Long id);

    @Query("SELECT f FROM Fifo f WHERE f.detailAgr.id = :x and f.ficheODD.id = :y ORDER BY f.dateCreate")
    public List<Fifo> ListFifoByByDetailAgrIdAndFicheODDId(@Param("x") Long id, @Param("y") Long id2);*/

    @Query(value = "SELECT f.* FROM fifo f, detail_agr d,type_avr t,avr a where a.id_type_avr = t.id AND a.id_detail_agr=d.id AND d.id=f.id_detail_agr AND t.libelle=:x ORDER BY f.dateCreate", nativeQuery = true)
    public List<Fifo> ListFifoByTypeAvr(@Param("x") String libelle);

    /*List<Fifo> listFifoByDetailAgr(Long idDetailAgr);*/

    /*@Query(value = "SELECT f.* FROM fifo f, detail_agr d,type_avr t,avr a where a.id_type_avr = t.id AND a.id_detail_agr=d.id AND d.id=f.id_detail_agr AND t.libelle='achat' ORDER BY f.dateCreate", nativeQuery = true)
    public List<Fifo> ListFifoByTypeAvrAchat();

    @Query(value = "SELECT f.* FROM fifo f, detail_agr d,type_avr t,avr a where a.id_type_avr = t.id AND a.id_detail_agr=d.id AND d.id=f.id_detail_agr AND t.libelle='vente' ORDER BY f.dateCreate", nativeQuery = true)
    public List<Fifo> ListFifoByTypeAvrVente();*/

    /*@Query("select f from Fifo f where f.detailAgr.agr.libelle = :x")
    public List<Fifo> listFifoByDetailAgr(@Param("x") String libelle);*/

    /***************************************************************************************************************
     *
     *
     *              PRINCE PATRICE dkp 21/07/2022
     *
     *
     * **/

    Fifo findFifoById(Long id);

    @Query("select f from Fifo f where f.isBuy = ?1")
    List<Fifo> getAllByType(boolean isBuy);

    @Query("select f from Fifo f where f.isBuy = ?1")
    List<Fifo> getLastByType(boolean isBuy, Pageable limit);

    @Query(value = "SELECT * FROM `fifo` f, details_agr d WHERE f.id_detail_agr = d.id and d.id_ksu <> ?1 and f.is_buy = true ", nativeQuery = true)
    List<Fifo> getLastByTypeDiffKsu(Long idKsu, Pageable limit);

    @Query("select f from Fifo f where f.isBuy = ?1 and f.avr.id = ?2")
    Fifo   getLastByTypeAndAvr(boolean isBuy, Long idAvr, PageRequest of);

    @Query(value = "select f.* from fifo f, details_agr d  where f.is_buy=?1 and f.id_detail_agr=d.id and d.id_ksu <> ?2 order by id ", nativeQuery = true)
    List<Fifo> getFirstAchatFromExtern(boolean bn , Long currentFirstVenteTe, Pageable limit);

    @Query(value = "select k.id from details_agr d, ksu k  where d.id_ksu =k.id and d.id= ?1 ", nativeQuery = true)
    Long getKsu(Long idDetailAgr, PageRequest of);

    @Query("Select f from Fifo f where f.detailAgr = :x  ")
    List<Fifo> getListByIdDetailAgr(@Param("x") Long id);

    /***************************************************************************************************************
     *
     *
     *              JamesLeMaitre  01/02/2023
     *
     *
     * **/

    @Query("select f from Fifo f where f.ksuType=?1 and f.isTreated=?2 ")
    List<Fifo> findAllByKsuTypeAndTreated(KsuType ksuType, boolean treated);

    @Query("select f from Fifo f where f.ksu.id=?1 and f.isTreated=false ")
    Fifo getFifoById(Long ksu);

    @Query("select f from Fifo f where f.ksu.id=?1 and f.ksuType=?2 and f.isTreated =false and f.panierTour=false")
        Fifo getFifoByKsuAndKsuType(Long ksu, KsuType ksuType);


//    @Override
//    //@PreAuthorize("hasRole('ROLE_ADMIN')")
//    <S extends Fifo> S save(S entity);
}

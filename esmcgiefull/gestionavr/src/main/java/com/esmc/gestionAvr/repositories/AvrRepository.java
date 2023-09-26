package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.Avr;
import com.esmc.gestionAvr.tokens.entities.ReferencToken;
import com.esmc.models.Ksu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvrRepository extends JpaRepository<Avr, Long> {


    @Query("SELECT a FROM Avr a WHERE a.detailAgr= :x  ")
    public List<Avr> listAvrByDetailAgrId(@Param("x") Long id);

/*    @Query("SELECT a FROM Avr a WHERE a.libelle LIKE %:x%")
    List<Avr> listAvrByLibelle(@Param("x") String libelle);*/

    @Query("SELECT a from Avr a where a.detailAgr = :x")
    public Avr avrByKsu(@Param("x") Long id);

    @Query("SELECT a FROM Avr a WHERE a.typeAvr.id= :x")
    public List<Avr> listAvrByType(@Param("x") Long id);

    /*@Query("SELECT a FROM Avr a WHERE a.id = :x AND a.typeAvr.id = :y")
    public Avr getAvrById(@Param("x") Long id1, @Param("y") Long id2);*/

    @Query("SELECT a FROM Avr a WHERE a.categorieAvr.id= :x")
    public List<Avr> listAvrByCategorie(@Param("x") Long id);

    @Query("SELECT a FROM Avr a WHERE a.detailAgr = :x AND a.typeAvr.id = :y")
    public List<Avr> listAvrByDetailAgrIdAndType(@Param("x") Long id, @Param("y") Long type);

    @Query("SELECT a FROM Avr a WHERE a.categorieAvr.id = :x AND a.typeAvr.id = :y and a.detailAgr = :z")
    public List<Avr> listAvrByCategorieAvrIdAndType(@Param("x") Long id, @Param("y") Long type, @Param("z") Long id2);

    @Query("SELECT a FROM Avr a WHERE a.categorieAvr.id = :z AND a.typeAvr.id = :y AND a.libelle LIKE %:x%")
    public List<Avr> listBesoinsAvr(@Param("x") String libelle, @Param("y") Long type,  @Param("z") Long categorie);

    @Query("SELECT a FROM Avr a WHERE a.detailAgr= :x")
    public Avr avrByDetailAgrId(@Param("x") Long id);

    Avr findById(Ksu ksuId);

    @Query("SELECT a FROM Avr a WHERE a.productRegistryValue.id = :x")
   Avr listAvrProductFifo(@Param("x") Long idPro);





}

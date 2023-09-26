package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.EtapeFranchise;
import com.esmc.gestionAchatFranchise.entities.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long > {

    Optional<Franchise> findByDetailAgr(Long id);

    @Query("select f from Franchise f where  f.centreFranchise.id= :x")
    public List<Franchise> findFranchiseByCentre(@Param("x") Long id);


    @Query("select f from Franchise f where  f.typeFranchise.id= :x")
    public  List<Franchise> findFranchiseByType(@Param("x") Long id);


    @Query("select f from Franchise f where  f.detailAgr= :x")
    public  List<Franchise> findFranchiseByDetailAgr(@Param("x") Long id);

    @Query("select f from Franchise f where  f.typeFranchise.id= :x and f.detailAgr = :y ")
    public  List<Franchise> findFranchiseByTypeAndDetailAgr(@Param("x") Long id, @Param("y") Long id2);

    @Query("select f from Franchise f where  f.typeFranchise.id= :x and f.centreFranchise.id = :y ")
    public  List<Franchise> findFranchiseByTypeAndCentre(@Param("x") Long id, @Param("y") Long id2);

    // QUERY TO RETRIEVE FRANCHISE BY TYPEFRANCHISE CENTRE AND DETAILAGR
    @Query("select f from Franchise f where  f.typeFranchise.id= :x and f.centreFranchise.id = :y and f.detailAgr = :z")
    public  List<Franchise> findFranchiseByTypeAndCentreAndDetailAgr(@Param("x") Long id, @Param("y") Long id2, @Param("z") Long id3);

    // QUERY TO RETRIEVE FRANCHISE BY TYPEFRANCHISE KSU AND DETAILAGR
    @Query(value= "SELECT * FROM ksu u, type_franchise tf, franchise f, detail_agr da\n" +
            "WHERE f.id_type_franchise=tf.id\n" +
            "AND f.id_detail_agr= da.id\n" +
            "AND da.id_ksu=u.id  ", nativeQuery = true)
    public  List<Franchise> findFranchiseByTypeAndKsuAndDetailAgr(@Param("x") Long id, @Param("y") Long id2, @Param("z") Long id3);






}

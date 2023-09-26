package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.DetailCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailCentreRepository extends JpaRepository<DetailCentre, Long> {

    @Query("select dc from  DetailCentre dc where  dc.agenceOdd.id= :x")
    public  List<DetailCentre> findDetailCentreByAgenceOdd(@Param("x") Long id);

    @Query("select dc from  DetailCentre dc where  dc.centre.id= :x")
    public List<DetailCentre> findDetailCentreByCentre(@Param("x") Long id);

    @Query("select dc from  DetailCentre dc where  dc.agenceOdd.id= :x and dc.centre.id = :y ")
    public  List<DetailCentre> findDetailCentreByCentreAndAgenceOdd(@Param("x") Long id, @Param("y") Long id2);
}


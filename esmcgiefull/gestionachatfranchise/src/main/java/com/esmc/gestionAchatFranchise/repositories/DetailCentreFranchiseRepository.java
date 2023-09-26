package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.DetailCentreFranchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailCentreFranchiseRepository extends JpaRepository<DetailCentreFranchise, Long > {

    @Query("select dcf from DetailCentreFranchise dcf where  dcf. centreFranchise.id= :x")
    public List<DetailCentreFranchise> findDetailCentreFranchiseByCentreFranchise(@Param("x") Long id);


    @Query("select dcf from DetailCentreFranchise dcf where  dcf.canton.id= :x")
    public  List<DetailCentreFranchise> findDetailCentreFranchiseByCanton(@Param("x") Long id);

    @Query("select dcf from DetailCentreFranchise dcf where  dcf.centreFranchise.id= :x and dcf.canton.id = :y ")
    public  List<DetailCentreFranchise> findDetailCentreFranchiseByCentreFranchiseAndCanton(@Param("x") Long id, @Param("y") Long id2);
}

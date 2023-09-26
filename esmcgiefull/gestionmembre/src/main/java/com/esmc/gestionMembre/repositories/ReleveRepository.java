package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.Releve;
import com.esmc.models.Ksu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleveRepository extends JpaRepository<Releve, Long> {
    @Query("SELECT a FROM Releve a WHERE  a.ksu = :x")
    List<Releve> listeReleveKsu(@Param("x") Ksu a);
    @Query("SELECT a FROM Releve a WHERE  a.ksu = :x ")
    Releve releveDeKsu(@Param("x") Ksu a);

    @Query("select r from Releve r where r.ksu = :x and r.terminalEchange = :y")
    public List<Releve> listReleveByKsu(@Param("x") Long id, @Param("y") Long idTe);

    @Query("select r from Releve r where r.origine = :x and r.type like :y% and r.membre = :z  and r.ksu = :k and r.terminalEchange = :h ")
    public List<Releve> listReleveBySystemAndCodeAndKsu(@Param("x") String sys, @Param("y") String produit, @Param("z") String code,  @Param("k") Long id, @Param("h") Long idTe);

    @Query("select distinct(r.type) from Releve r where r.ksu = :x and r.terminalEchange = :y and r.cloture = false")
    public List<String> listReleveByProduit(@Param("x") Long id, @Param("y") Long idTe);

    @Query("select r from Releve r where r.ksu = :x and r.terminalEchange = :y  and r.type = :z and r.cloture = false")
    public List<Releve> listReleveByKsuAndProduit(@Param("x") Long id, @Param("y") Long idTe, @Param("z")  String produit);

    @Query("select r from Releve r where r.id = :x and r.montant = :y ")
    public Releve getReleve(@Param("x") Long id, @Param("y")  double valeur);


}

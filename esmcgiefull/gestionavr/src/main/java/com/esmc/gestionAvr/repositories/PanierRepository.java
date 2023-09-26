package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PanierRepository extends JpaRepository<Panier, Long> {

    @Query("SELECT a FROM Panier a WHERE a.ksu = :x")
    public List<Panier> panierDeKsu(@Param("x") Long id);

    @Query("select sum(p.quantite * p.avr.prixUnitaire) from Panier p where p.ksu = :x")
    public Double prixTotalPanier(@Param("x") Long id);

    @Query("select p from Panier p where p.ksu = :x and p.avr.id = :y")
    public Panier findPanierByKsuAndAvr(@Param("x") Long id,@Param("y") Long id2);

    @Query("SELECT a FROM Panier a WHERE a.ksu = :x and a.etat = false ")
   public List<Panier> listPanierFalseByKsu(@Param("x") Long id);

}

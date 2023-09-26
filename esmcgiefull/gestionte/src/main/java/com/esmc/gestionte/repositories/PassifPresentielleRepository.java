package com.esmc.gestionte.repositories;

import com.esmc.gestionte.entities.PassifPresentielle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PassifPresentielleRepository extends JpaRepository<PassifPresentielle,Long> {

    @Query("select p from PassifPresentielle p where p.idChiffrementQrcode = :x")
    public PassifPresentielle findPassifPresentiellebyIdEmpreinte(@Param("x") String id);

    @Query("select p from PassifPresentielle p where  p.noRef=?1")
    public PassifPresentielle findPassifPresentiellebyNumRef( String numRef);

    @Query("select p from PassifPresentielle p where  p.status=true and p.idChiffrementQrcode = :x or p.noRef = :x")
    public PassifPresentielle findPassifPresentiellByQrCodeOrNUmRef(@Param("x") String code);


    PassifPresentielle getPassifPresentielleById(Long id);

    @Query("select p from PassifPresentielle p where  p.valReinitBCi = :x or p.valeurInitialXOF = :x")
    PassifPresentielle getPassifPresentielleByvalReinitBCi(@Param("x") Double valReinitBCi);

    @Query("select p from PassifPresentielle p where  UPPER(p.nomVendeur) = :x and UPPER(p.prenomsvendeur) = :y")
    PassifPresentielle getPassifPresentielleByNomAndPrenom(@Param("x") String nom, @Param("y") String prenom);

    @Query("select p.numBonCommande from PassifPresentielle p where p.id=:x")
    String returnNumBonComm(@Param("x") Long id);
}

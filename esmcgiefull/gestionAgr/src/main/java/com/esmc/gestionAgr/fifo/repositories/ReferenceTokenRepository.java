package com.esmc.gestionAgr.fifo.repositories;


import com.esmc.gestionAgr.fifo.entities.ReferencToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReferenceTokenRepository extends JpaRepository<ReferencToken, Long> {
    @Query("SELECT count(r)  from ReferencToken r ")
    int Counter();

    @Query("SELECT r  from ReferencToken r  WHERE r.id=:x and r.etat=true")
    ReferencToken getByIdAvr(@Param("x") Long id);

    @Query("SELECT r  from ReferencToken r  WHERE r.id=:x")
    ReferencToken getById(@Param("x") Long id);


    @Query("SELECT r  from ReferencToken r  WHERE r.codeRef=:x")
    ReferencToken getByCodeRef(@Param("x") String id);

    ReferencToken findReferencTokenByCodeRef(String codeRef);


    @Query("SELECT r  from ReferencToken r  WHERE r.refTokenFront=:x")
    ReferencToken getByRefTokenFront(@Param("x") String id);


    @Query("SELECT r  from ReferencToken r  WHERE r.idParent=:x")
    ReferencToken findByIdParent(@Param("x") Long id);

    @Query("SELECT r  from ReferencToken r  WHERE r.avr.detailAgr=6 and r.etat=true")
    ReferencToken getByIdDetailAgrGIE();

    @Query("SELECT r FROM ReferencToken r LEFT JOIN FETCH r.avr WHERE r.id = :id")
    ReferencToken findTokenWithAvr(@Param("id") Long id);

    @Query("SELECT r FROM ReferencToken r LEFT JOIN FETCH r.avr WHERE r.id = :id AND r.idParent IS NOT NULL")
    ReferencToken findTokenWithParentAndAvr(@Param("id") Long id);

}

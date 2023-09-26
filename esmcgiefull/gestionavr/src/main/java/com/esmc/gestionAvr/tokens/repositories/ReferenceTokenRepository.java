package com.esmc.gestionAvr.tokens.repositories;

import com.esmc.gestionAvr.tokens.entities.ReferencToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReferenceTokenRepository extends JpaRepository<ReferencToken,Long> {
    @Query("SELECT count(r)  from ReferencToken r ")
    public int Counter();

    @Query("SELECT r  from ReferencToken r  WHERE r.id=:x and r.etat=true")
    ReferencToken getByIdAvr(@Param("x") Long id);

    @Query("SELECT r  from ReferencToken r  WHERE r.avr.id=:x and r.etat=true")
    ReferencToken getByIdAvr2(@Param("x") Long id);

    @Query("SELECT r  from ReferencToken r  WHERE r.avr.detailAgr=6 and r.etat=true")
    ReferencToken getByIdDetailAgrGIE();

    @Query("SELECT r  from ReferencToken r  WHERE r.id=:x")
    public ReferencToken getById(@Param("x") Long id);


    @Query("SELECT r  from ReferencToken r  WHERE r.codeRef=:x")
    public ReferencToken getByCodeRef(@Param("x") String id);
    ReferencToken findReferencTokenByCodeRef(String codeRef);


    @Query("SELECT r  from ReferencToken r  WHERE r.refTokenFront=:x")
    public ReferencToken getByRefTokenFront(@Param("x") String id);

    @Query("select r from ReferencToken r where r.etat = true  and r.avr.detailAgr=?1 ")
    public ReferencToken getReferenceTokenGIEActif(Long idDetailAgr);
    @Query("SELECT r  from ReferencToken r  WHERE r.idParent=:x")
    ReferencToken findByIdParent(@Param("x") Long id);
    @Query("SELECT r FROM ReferencToken r   LEFT JOIN FETCH r.avr WHERE r.id = :id AND r.idParent IS  NULL ")
    ReferencToken findTokenWithParentAndAvr(@Param("id") Long id);
}

package com.esmc.gestionAvr.tokens.repositories;


import com.esmc.gestionAvr.tokens.entities.ReferencToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author Amemorte9
 * @since 31/01/2023
 */
@Repository
public interface ReferencTokenRepo extends JpaRepository<ReferencToken,Long> {


    @Query("SELECT count(r)  from ReferencToken r ")
    public int Counter();




    @Query("SELECT r  from ReferencToken r  WHERE r.avr.id=:x and r.etat=true")
    ReferencToken getByIdAvr(@Param("x") Long id);



    @Query("SELECT r  from ReferencToken r  WHERE r.id=:x and r.etat=true")
    public ReferencToken getById(@Param("x") Long id);


    @Query("SELECT r  from ReferencToken r  WHERE r.codeRef=:x and r.etat=true")
    public ReferencToken getByCodeRef(@Param("x") String id);


    @Query("SELECT r  from ReferencToken r  WHERE r.refTokenFront=:x and r.etat=true")
    public ReferencToken getByRefTokenFront(@Param("x") String id);

}

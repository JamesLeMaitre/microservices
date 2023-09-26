package com.esmc.gestionAgr.repositories;

import com.esmc.gestionAgr.entities.Agr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgrRepository extends JpaRepository<Agr,Long> {
    //SELECT * FROM agr WHERE dateCreate =?

    @Query("SELECT a FROM Agr a WHERE trim(lower(a.libelle)) =trim(lower(?1)) ")
    Optional<Agr> findAgrByLibelle(String libelle);


/*    @Query("select a from Agr a where libelle IN ('OI','OKSU','OP','OT')")
    public List<Agr> listAgrPP();

    @Query("select a from Agr a where libelle IN ('OBPSD','OBPS','OP','OT')")
    public List<Agr> listAgrPM();*/


}

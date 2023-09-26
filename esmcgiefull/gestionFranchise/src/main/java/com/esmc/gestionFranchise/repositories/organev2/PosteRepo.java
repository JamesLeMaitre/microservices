package com.esmc.gestionFranchise.repositories.organev2;

import com.esmc.gestionFranchise.entities.organev2.Poste;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;


@Repository
public interface PosteRepo extends JpaRepository<Poste,Long> {


    @Query("select p from Poste p where p.structure.id=:x")
    List<Poste> findPostByIdTypePoste(@Param("x") Long id);

    @Query("select p from Poste p where p.posteParent.id =:x")
    List<Poste> getPosteByParent(@Param("x") Long id);


    @Query("select p from Poste p where p.status=false")
    List<Poste> getAllNotActivePoste();

    @Query("select p.id,p.libelle from Poste p where p.status=false")
    Page<Poste> getAllNo(Pageable pageable);

    @Query("select DISTINCT p from Poste p where p.posteParent.id not in (:x)")
    List<Poste> getWithoutId(@Param("x") Long id);

    @Query("select p.id,p.libelle from Poste p where p.status=false")
    List<Poste> getNewList();

}

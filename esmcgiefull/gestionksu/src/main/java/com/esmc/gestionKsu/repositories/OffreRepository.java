package com.esmc.gestionKsu.repositories;

import com.esmc.gestionKsu.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * {@link Offre} repository
 * @author katoh <katohdavid@gmail.com>
 */
public interface OffreRepository extends JpaRepository<Offre,Long> {

    @Query("select o from Offre o where lower(trim(o.libelle))=lower(trim(?1))")
    Optional<Offre> findByLibelle(String libelle);

    @Query("select o from Offre o where o.typePM.id = :x and o.typeOffre.id = :y")
    public List<Offre>  findOffresByTypePMAndTypeOffre(@Param("x") Long id, @Param("y") Long id2);

}

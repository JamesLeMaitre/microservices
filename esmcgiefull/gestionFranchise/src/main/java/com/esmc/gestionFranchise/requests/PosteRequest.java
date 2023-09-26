package com.esmc.gestionFranchise.requests;

import com.esmc.gestionFranchise.entities.organev2.Poste;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PosteRequest extends JpaRepository<Poste,Long> {
    @Query("select p.id,p.libelle from Poste p")
    List<Poste> getAllListV2(Pageable pageable);
}

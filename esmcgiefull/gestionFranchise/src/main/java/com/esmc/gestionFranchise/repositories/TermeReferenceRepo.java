package com.esmc.gestionFranchise.repositories;

import com.esmc.gestionFranchise.entities.TermeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TermeReferenceRepo extends JpaRepository<TermeReference,Long> {

    @Query("select t from TermeReference t where  t.intervenant.id=?1")
    public List<TermeReference> TermeReferenceByIntervenant(Long id);

}

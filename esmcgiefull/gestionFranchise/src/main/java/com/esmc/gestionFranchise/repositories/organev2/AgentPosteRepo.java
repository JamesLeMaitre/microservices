package com.esmc.gestionFranchise.repositories.organev2;

import com.esmc.gestionFranchise.entities.organev2.AgentPoste;
import com.esmc.models.FranchiseReferentiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentPosteRepo extends JpaRepository<AgentPoste,Long> {
    //@Query("select a from AgentPoste a where a.idFranchise = :x")
    //List<AgentPoste> findByFranchise(@Param("x")  Long idFranchise);

    @Query(value = "select a from AgentPoste a where a.idFranchise = :x")
    List<AgentPoste> findByFranchise(@Param("x")  Long idFranchise);

    @Query(value = "select a.idFranchise from AgentPoste a where a.idDetailAgr = :x")
    List<Long> list_franchise_by_idDetailAgr(@Param("x") Long idKsu);

    @Query(value = "select a from AgentPoste a where a.agentRecruteur.id = :x")
    AgentPoste findByAgentRecruteur(@Param("x") long id);

    @Query(value = "select a from AgentPoste a where a.idAgentPoste = :x")
    AgentPoste findByIdv2(@Param("x") long id);



    @Query(value = "select a from AgentPoste a where a.poste.id IN (:x) and a.idFranchise= :y")
    List<AgentPoste> findByPost(@Param("x")  List<Long> values, @Param("y")  Long idFranchise);

    @Query(value = "select a from AgentPoste a where a.idDetailAgr = :x")
    List<AgentPoste> getByIdDetailAgr(@Param("x") Long id);

    @Query("select a from AgentPoste a where a.idDetailAgrFranchise = :x")
    List<AgentPoste> listAgentPoste(@Param("x") Long idDetailAgrFranchise);

    @Query("select a from AgentPoste a where a.idDetailAgr = :x and a.poste.id=:y and a.idFranchise=:z")
    AgentPoste getAgentPoste(@Param("x") Long idx,@Param("y") Long idy,@Param("z") Long idz);
}

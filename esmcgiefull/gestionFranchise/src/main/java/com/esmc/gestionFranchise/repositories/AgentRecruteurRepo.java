package com.esmc.gestionFranchise.repositories;

import com.esmc.gestionFranchise.entities.AgentRecruteur;
import com.esmc.gestionFranchise.entities.organev2.AgentPoste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AgentRecruteurRepo extends JpaRepository<AgentRecruteur,Long> {
   // @Query("select new com.esmc.gestionFranchise.entities.Custome(ar.id,dca.conseilAdministration.libelle,do.organe.libelle) from AgentRecruteur ar,DetailOrgane do , DetailConseilAdministration  dca  where ar.id=:x  and   dca.agentRecruteur.id= :x  or  do.agentRecruteur.id=:x ")
    //List<AgentRecruteur> getTitle(@Param("x") Long id);
    @Query("select a from AgentRecruteur a where a.codePovoirFaire = :x ")
    AgentRecruteur findByCodePouvoirFaire(@Param("x")  String codePouvoirFaire);

    @Query("select a from AgentRecruteur a where a.idAgr = :x  and a.idDetailsAgrFranchiser=:y and a.idKsu=:z")
    AgentRecruteur getCodePv(@Param("x") Long idx,@Param("y") Long idy,@Param("z") Long idz);


    @Query("select a from AgentPoste a where a.poste.id =:x ")
    List<AgentPoste> getAgentPoste(@Param("x")  Long id);

    @Query("select a from AgentPoste a where a.agentRecruteur.id = :x ")
    List<AgentRecruteur>  findByCodePouvoirFaire(@Param("x")  Long id);
}

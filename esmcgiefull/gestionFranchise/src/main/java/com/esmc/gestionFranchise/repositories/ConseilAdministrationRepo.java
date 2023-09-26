package com.esmc.gestionFranchise.repositories;


import com.esmc.gestionFranchise.entities.ConseilAdministration;
import com.esmc.gestionFranchise.entities.FichePoste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConseilAdministrationRepo extends JpaRepository<ConseilAdministration,Long> {

    @Query("select ca from  ConseilAdministration ca where  ca.franchise= :x")
    List<ConseilAdministration> getConseilAdministrationbyFranchise(@Param("x") Long id);

}

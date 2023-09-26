package com.esmc.gestionFranchise.repositories;


import com.esmc.gestionFranchise.entities.ConseilAdministration;
import com.esmc.gestionFranchise.entities.Organe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganeRepo extends JpaRepository<Organe,Long> {
    @Query("select o from  Organe o where  o.conseilAdministration.id= :x")
    List<Organe> getOrganebyCadmin(@Param("x") Long id);
}

package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.Continent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long> {
    @Query("select count(co) from Continent co where  co.isBuy = false")
    int getCountFreeContinentByMonde();

    @Query("select count(co) from Continent co where  co.isBuy = true")
    int getCountBuyContinentByMonde();
}

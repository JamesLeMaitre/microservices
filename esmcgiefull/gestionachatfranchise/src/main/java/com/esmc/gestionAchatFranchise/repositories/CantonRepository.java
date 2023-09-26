package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.Canton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CantonRepository extends JpaRepository<Canton, Long> {

    @Query("select c from Canton c where  c.commune.id= :x")
    public List<Canton> findCantonByCommune(@Param("x") Long id);

    @Query("select count(c) from Canton c where  c.commune.id= :x and c.isBuy=false")
    int getCountFreeCantonByCommune(@Param("x") Long idFranchise);

    @Query("select count(c) from Canton c where  c.commune.id= :x and c.isBuy=true")
    int getCountBuyCantonByCommune(@Param("x") Long idFranchise);
}

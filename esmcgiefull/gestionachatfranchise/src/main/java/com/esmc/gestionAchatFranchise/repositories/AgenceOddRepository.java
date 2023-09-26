package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AgenceOddRepository extends JpaRepository<AgenceOdd, Long> {


    @Query("select a from AgenceOdd a order by a.nombre")
    public List<AgenceOdd> listAgenceOddByNumber();


}

package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.Tpagcp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TpagcpRepository extends JpaRepository<Tpagcp, Long> {
    @Query("SELECT a FROM Tpagcp a WHERE a.codeMembre = :x")
    List<Tpagcp> passifsESMCSARLUTpagcp(@Param("x") String code);

    @Query("SELECT a FROM Tpagcp a WHERE a.codeMembre = :x")
    List<Tpagcp> listTpagcp(@Param("x") String code);
}

package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.AncienGcp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AncienGcpRepository extends JpaRepository<AncienGcp, Long> {
    @Query("SELECT a FROM AncienGcp a WHERE a.codeMembre = :x")
    AncienGcp passifsMCNPAncienGcp(@Param("x") String code);

    @Query("SELECT coalesce(sum(a.montGcp),0) FROM AncienGcp a WHERE a.codeMembre = :x")
    public Double sommepassifsMCNPGcp(@Param("x") String code);

    @Query("SELECT a FROM AncienGcp a WHERE a.codeMembre = :x")
    public List<AncienGcp> listpassifMCNPGcp(@Param("x") String code);

    @Query("SELECT a FROM AncienGcp a WHERE trim(lower(a.codeMembre)) =trim(lower( :x))")
    public List<AncienGcp> findAncienGcpByCode(@Param("x") String code);
}

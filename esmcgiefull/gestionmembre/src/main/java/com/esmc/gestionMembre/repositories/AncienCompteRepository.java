package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.AncienCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AncienCompteRepository extends JpaRepository<AncienCompte, String> {

    @Query("SELECT ac FROM AncienCompte ac WHERE ac.codeMembre = :x AND (ac.code_cat = 'TCNCSEI' OR ac.code_cat = 'TPN' OR  ac.code_cat  = 'TCNCS')")
    public List<AncienCompte> listAncienCompteCncs(@Param("x") String code);

}

package com.esmc.demandeAchatBanKsu.repositories;

import com.esmc.demandeAchatBanKsu.entities.MABanOI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MABanOIRepository extends JpaRepository<MABanOI,Long> {

}

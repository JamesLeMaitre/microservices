package com.esmc.demandeAchatBanKsu.repositories;

import com.esmc.demandeAchatBanKsu.entities.TypeMABanKSU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMABanKSURepository extends JpaRepository<TypeMABanKSU,Long> {

}

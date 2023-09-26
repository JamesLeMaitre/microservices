package com.esmc.demandeAchatBanKsu.repositories;

import com.esmc.demandeAchatBanKsu.entities.Errors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRepository extends JpaRepository<Errors,Long> {
}

package com.esmc.gestionte.repositories;

import com.esmc.gestionte.entities.PassifPresentielle;
import com.esmc.gestionte.entities.PassifPresentielleHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassifPresentielleHistoryRepository extends JpaRepository<PassifPresentielleHistory,Long> {
}

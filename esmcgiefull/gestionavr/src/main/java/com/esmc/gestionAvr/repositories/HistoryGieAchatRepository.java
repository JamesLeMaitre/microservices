package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.HistoryGieAchat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HistoryGieAchatRepository  extends JpaRepository<HistoryGieAchat, Long> {
    @Query("Select SUM(h.bciValue) from HistoryGieAchat h")
    Double getCumule();
}
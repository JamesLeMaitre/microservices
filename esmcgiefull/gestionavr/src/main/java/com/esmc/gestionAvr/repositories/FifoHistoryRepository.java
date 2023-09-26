package com.esmc.gestionAvr.repositories;


import com.esmc.gestionAvr.entities.FifoHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FifoHistoryRepository extends JpaRepository<FifoHistory, Long> {
    @Query("Select fv from FifoHistory fv where fv.detailAgr = :x  ")
    List<FifoHistory> getListByIdDetailAgr(@Param("x") Long id);
    @Query("Select fv from FifoHistory fv where fv.isBuy = :x  ")
    List<FifoHistory> getListByType(@Param("x") Boolean isBuy);
}
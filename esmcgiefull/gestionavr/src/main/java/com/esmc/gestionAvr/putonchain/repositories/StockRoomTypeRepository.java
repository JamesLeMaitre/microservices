package com.esmc.gestionAvr.putonchain.repositories;

import com.esmc.gestionAvr.putonchain.entities.StockRoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRoomTypeRepository extends JpaRepository<StockRoomType,Long> {
    Optional<StockRoomType> findById(Long id);
    List<StockRoomType> findByCenterType_Id(Long id);
}

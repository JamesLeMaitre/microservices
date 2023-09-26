package com.esmc.gestionAvr.putonchain.repositories;

import com.esmc.gestionAvr.putonchain.entities.Category;
import com.esmc.gestionAvr.putonchain.entities.ParameterProducts;
import com.esmc.gestionAvr.putonchain.entities.StockRoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParameterProductsRepository extends JpaRepository<ParameterProducts,Long> {
    Optional<ParameterProducts> findByStockRoomTypeAndCategory(StockRoomType stockRoomType, Category category);
}

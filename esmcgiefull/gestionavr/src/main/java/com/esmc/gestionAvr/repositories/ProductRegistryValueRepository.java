package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.ProductRegistryValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRegistryValueRepository extends JpaRepository<ProductRegistryValue, Long> {
    @Query("select coalesce(sum(p.value), 0) from ProductRegistryValue p where p.idKsu = :x")
    Double amountBciAvailable(@Param("x") Long idKsu);

    @Query("select p from ProductRegistryValue p where p.status = true and p.idKsu = :x")
    List<ProductRegistryValue> productRegistryValueList(@Param("x") Long idKsu);
}
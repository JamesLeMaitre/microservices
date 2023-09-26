package com.esmc.gestionAvr.putonchain.repositories;

import com.esmc.gestionAvr.putonchain.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products,Long> {
    Optional<Products> findById(Long id);
    Optional<Products> findByIdAndStateIsTrue(Long id);

    List<Products> findByIdDetailAgrAndStateIsTrue(Long idDetailAgr);
}

package com.esmc.gestionformation.repositories;


import com.esmc.gestionformation.entities.TypeCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeCodeRepository extends JpaRepository<TypeCodes, Long> {
    @Query("select t from TypeCodes t where t.id = 2")
    TypeCodes getTypeCodesById();
}

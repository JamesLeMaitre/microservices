package com.esmc.gestionAgr.repositories;

import com.esmc.gestionAgr.entities.TypeMaBanKsuAgr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeMaBanKsuAgrRepository extends JpaRepository<TypeMaBanKsuAgr, Long> {

    @Query("select t from TypeMaBanKsuAgr t where t.IdMaBanKsu = :id")
    public List<TypeMaBanKsuAgr> listTypeMaBanKsuAgrByTypeMaBanKsu(@Param("id") Long id);

}

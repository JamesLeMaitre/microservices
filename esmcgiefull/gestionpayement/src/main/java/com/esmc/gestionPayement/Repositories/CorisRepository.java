package com.esmc.gestionPayement.Repositories;

import com.esmc.gestionPayement.entities.coris.Coris;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CorisRepository extends JpaRepository<Coris, Long> {



    @Query("select c from Coris c where trim(lower(c.transactionId))=trim(lower(:x)) and c.status=true")
    Coris findByCodeRef(@Param("x") String code);

}

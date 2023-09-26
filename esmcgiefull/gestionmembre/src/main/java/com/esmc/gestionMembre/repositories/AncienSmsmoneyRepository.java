package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.AncienSmsmoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface AncienSmsmoneyRepository extends JpaRepository<AncienSmsmoney, Long> {

    @Query("select sum(a.creditAmount) from AncienSmsmoney a where a.fromAccount = :x and a.motif in ('FS', 'FL', 'CPS') and a.idDateTimeConsumed = 0")
    public Double sommeAncienSmsmoney(@Param("x") String code);

    @Query("select sum(a.creditAmount) from AncienSmsmoney a where a.fromAccount = :x and a.motif in ('FS', 'FL', 'CPS') and a.idDateTimeConsumed = 0")
    public List<AncienSmsmoney> listAncienSmsmoney(@Param("x")  String code);

}

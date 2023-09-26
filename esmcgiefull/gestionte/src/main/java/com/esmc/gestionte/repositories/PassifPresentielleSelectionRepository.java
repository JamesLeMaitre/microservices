package com.esmc.gestionte.repositories;

import com.esmc.gestionte.entities.PassifPresentielle;
import com.esmc.gestionte.entities.PassifPresentielleSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PassifPresentielleSelectionRepository extends JpaRepository<PassifPresentielleSelection,Long> {
    @Query("select p from PassifPresentielleSelection p where p.idPassifPresentielle = :x")
    PassifPresentielleSelection findPassifPresentiellSelectionidPassifPresentielle(@Param("x") Long id);
    @Query("select p from PassifPresentielleSelection p where p.newCode = :x  and p.dateActivated >:y  ")
    PassifPresentielleSelection findPassifPresentiellSelectionidPassifPresentielleByCodeTime(@Param("x") String code,@Param("y") Long timeToBeLessThanActivateTime);
}

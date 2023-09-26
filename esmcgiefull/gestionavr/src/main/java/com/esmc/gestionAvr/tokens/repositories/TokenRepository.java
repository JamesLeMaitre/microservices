package com.esmc.gestionAvr.tokens.repositories;

import com.esmc.gestionAvr.tokens.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends JpaRepository<Token,Long> {
    @Query("select tk from Token tk where tk.tokens =:x")
    Token getByTokens(@Param("x")String token);
}

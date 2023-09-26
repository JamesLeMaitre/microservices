package com.esmc.demandeAchatBanKsu.repositories;

import com.esmc.demandeAchatBanKsu.entities.BanKsu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


public interface BanKsuRepository extends JpaRepository<BanKsu,Long> {
    @Query("SELECT a FROM BanKsu a WHERE trim(lower(a.codeBanKsu)) = trim(lower(?1))")
    public BanKsu findBanKsuByCodeBanKsu( String codeBanKsu);

    @Query(value = "SELECT libelle FROM typemabanksu t, ban_ksu b, ma_ban_ksu mb WHERE b.id_ma_ban_ksu  = mb.id AND mb.id_type_mabanksu = t.id  and trim(lower(b.code_ban_ksu)) =:x ",nativeQuery = true)
    public String typeKsu(@Param("x") String codeBanKsu);

    @Query("select b from BanKsu b where b.maBanKsu.nom = :x and b.maBanKsu.prenom = :y and b.maBanKsu.email = :z")
    public BanKsu getExitingBanKsu(@Param("x") String nom, @Param("y") String prenom, @Param("z") String email);

    @Query("select b from BanKsu b where b.maBanKsu.id = :x and b.codeBanKsu = :y")
    public BanKsu getExitingBanKsuByCodeBanAndIdMaBanKsu(@Param("x") Long idMabansu, @Param("y") String codeBan);

    @Query("select b from BanKsu b where b.maBanKsu.id = :x")
    public BanKsu getBanKsuByIdMaBanKsu(@Param("x") Long id);

    @Query("select b from BanKsu b where b.codeBanKsu = :x")
    public BanKsu getBanKsuByCodeBan(@Param("x") String code);

}

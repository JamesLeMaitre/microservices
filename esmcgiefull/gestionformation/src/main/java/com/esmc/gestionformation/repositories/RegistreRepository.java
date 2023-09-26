package com.esmc.gestionformation.repositories;

import com.esmc.gestionformation.entities.Registre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistreRepository extends JpaRepository<Registre,Long> {

    @Query("select c from Registre c where c.code.code=:x")
    Registre getRegistresByCode(@Param("x")String code);

    @Query("select c from Registre c where c.code.code=:x and c.salleFormation.id=:y and c.idKsu=:z and c.dailyCheck=false")
    Registre checkExist(@Param("x")String code,@Param("y")Long idy,@Param("z")Long idz);

    @Query("select c from Registre c where c.idKsu=:x and c.dateSortie is null")
    Registre checkOut(@Param("x")Long code);

    @Query("select c from Registre c where c.idPoste=:x and c.dateEntree is not null and c.dateSortie is not null and c.dailyCheck=true")
    Registre checkOutFranchiser(@Param("x")Long code);


    @Query("select c from Registre c where c.idPoste=:x and c.dateEntree is not null and c.dateSortie is not null and c.dailyCheck=true")
    List<Registre> getHistorique(@Param("x")Long salle);
}

package com.esmc.gestionformation.repositories;

import com.esmc.gestionformation.entities.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, Long> {

    @Query("Select c from Code c where c.typeCode.id  = :x")
    public List<Code> listCodeByType(@Param("x") Long idType);

    @Query("Select c from Code c where  c.affectationSalle.id = :x")
    public List<Code> listCodeBySalle(@Param("x") Long idSalle);

    @Query("Select c from Code c where c.typeCode.id = :x and c.affectationSalle.id = :y")
    public List<Code> listCodeByTypeAndSalle(@Param("x")  Long idType, @Param("y")  Long idSalle);



    @Query("Select c from Code c where c.code = :x")
    public Code getCodeByCode(@Param("x") String code);

    @Query("Select c from Code c where c.code=:x and c.affectationSalle.id=:y")
     Code getCodeByCodeCheckInit(@Param("x") String code,@Param("y")Long id );

    @Query("Select c.code from Code c where c.idAgr=:x and c.idAgrFranchiser=:y and c.affectationSalle.id=:z")
    public String getCodeByIdAgr(@Param("x") Long idAgr,@Param("y") Long idAgrFranchiser,@Param("z") Long idAffec);


    @Query(value = "Select count(c.id) from Code c WHERE  c.code like CONCAT(:x, '%')")
    public Integer  getLastIn(@Param("x") String code);

    @Query("Select c from Code c where c.idAgrFranchiser = :x and c.viewAdmin=false")
     List<Code> getCodeByFranchise(@Param("x") Long id);


   // UPDATE exportTmp SET numfactTMP = substring(numfactTMP,2,len(numfactTMP)-1) WHERE numfactTMP LIKE '[A-Z]%'





}

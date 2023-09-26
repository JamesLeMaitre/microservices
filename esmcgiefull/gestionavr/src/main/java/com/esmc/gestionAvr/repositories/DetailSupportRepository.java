package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.DataSupport;
import com.esmc.gestionAvr.entities.DetailSupport;
import com.esmc.models.TypeSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailSupportRepository  extends JpaRepository<DetailSupport, Long> {

    @Query("select d from DetailSupport d where d.idTypeSupport =:x")
    Long getByID(@Param("x") Long x);

    @Query("select d.id from DetailSupport d where d.idTypeSupport =:x")
    List<Long> getByIDV2(@Param("x") Long x);


}
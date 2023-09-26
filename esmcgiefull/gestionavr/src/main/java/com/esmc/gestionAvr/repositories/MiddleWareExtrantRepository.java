package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.MiddleWareExtrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiddleWareExtrantRepository extends JpaRepository<MiddleWareExtrant,Long> {
    @Query("select mx from MiddleWareExtrant  mx where mx.idExtrant=:x and mx.intervenant=:y and mx.step=true")
    MiddleWareExtrant updateBy(@Param("x")Long id, @Param("y")Long y);

    @Query("select mc from MiddleWareExtrant mc where mc.intervenant=:x and mc.step = true")
    List<MiddleWareExtrant> listIntrantTour(@Param("x")Long id);

    @Query("select mc from MiddleWareExtrant mc where mc.idExtrant=:x and mc.step = true")
    List<MiddleWareExtrant> listExtrantTour(@Param("x")Long id);

    @Query("select mc from MiddleWareExtrant mc where mc.intervenant=:x and mc.step = false")
    List<MiddleWareExtrant> listIntrantTourID(@Param("x")Long id);

    @Query("select mx from MiddleWareExtrant  mx where mx.idExtrant=:x and mx.step = true")
    MiddleWareExtrant archiverTourV2(@Param("x")Long id);
//
//    @Query("select mc from MiddleWareExtrant mc where mc.intervenant=:x and mc.step = true and mc.archiver=false and mc.vider=false")
//    List<MiddleWareExtrant> listIntrantTourV2(@Param("x")Long id);

}

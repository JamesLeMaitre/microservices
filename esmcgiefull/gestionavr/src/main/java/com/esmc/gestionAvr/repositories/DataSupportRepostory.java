package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.DataSupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataSupportRepostory extends JpaRepository<DataSupport, Long> {
    @Query("select d from DataSupport d where d.detailSupport.id=:x")
    DataSupport getDataSupport(@Param("x") Long id);
    
    @Query("select d from DataSupport d")
    List<DataSupport> getDataSupportPage(Pageable pageable);

    @Query("select d from DataSupport d where d.detailSupport.idTypeSupport=:x")
    Page<DataSupport> getDataSupportByDetailTypeSupportPage(@Param("x")Long id,Pageable pageable);

    @Query("select d from DataSupport d where d.detailSupport.idTypeSupport=:x ")
    DataSupport getDataSupportV2(@Param("x") Long id);

    @Query("select d from DataSupport d where d.detailSupport.id=:x and d.id is not null ")
    List<DataSupport> getDataSupportV3(@Param("x") Long id);

    @Query("select d from DataSupport d where d.detailTypeSupport.id=:x")
    DataSupport getDataTypeSupport(@Param("x") Long id);

    @Query("select d from DataSupport d where d.detailSupport.idTypeSupport=:x")
    List<DataSupport> getListBon(@Param("x") Long id);

    @Query("select d from DataSupport d where d.detailSupport.idTypeSupport=:x")
    Page<DataSupport> getListBonV2(@Param("x") Long id,Pageable pageable);
}

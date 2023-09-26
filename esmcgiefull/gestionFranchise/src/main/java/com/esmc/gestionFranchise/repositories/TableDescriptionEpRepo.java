package com.esmc.gestionFranchise.repositories;

import com.esmc.gestionFranchise.entities.TableDescriptionEp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableDescriptionEpRepo extends JpaRepository<TableDescriptionEp,Long> {

    @Query("select td from  TableDescriptionEp td where  td.manuelProcedure.id= :x")
    List<TableDescriptionEp> getTableaubyManuel(@Param("x") Long id);

    @Query("select t from TableDescriptionEp t where  t.manuelProcedure.id=?1")
    public List<TableDescriptionEp> findTableDescriptionEpBymanuelProcedure(Long id);

}

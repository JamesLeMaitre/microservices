package com.esmc.gestionFranchise.repositories.organev2;


import com.esmc.gestionFranchise.entities.organev2.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StructureRepo extends JpaRepository<Structure,Long> {

}

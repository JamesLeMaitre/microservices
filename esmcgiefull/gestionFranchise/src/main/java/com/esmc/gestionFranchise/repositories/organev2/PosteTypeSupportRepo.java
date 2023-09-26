package com.esmc.gestionFranchise.repositories.organev2;

import com.esmc.gestionFranchise.entities.organev2.PosteTypeSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosteTypeSupportRepo extends JpaRepository<PosteTypeSupport,Long> {
     @Query("select p from PosteTypeSupport p where p.receiverPoste.id =:x and p.senderPoste.id =:y and p.typeSupport.id=:z")
  List<PosteTypeSupport> getAllSupportByPoste(@Param("x")Long idReceiver, @Param("y")Long idSender, @Param("z")Long idTDEp);
}

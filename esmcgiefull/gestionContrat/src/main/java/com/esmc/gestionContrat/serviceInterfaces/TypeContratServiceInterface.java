package com.esmc.gestionContrat.serviceInterfaces;

import com.esmc.gestionContrat.entities.TypeContrat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface TypeContratServiceInterface {

     TypeContrat addTypeContrat(TypeContrat t);
     TypeContrat updateTypeContrat(TypeContrat typeContrat,Long id);
     void deleteTypeContrat(Long id);
     List<TypeContrat> listTypeContrat();
     TypeContrat getById(Long id);
}

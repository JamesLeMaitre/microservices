package com.esmc.gestionformation.services;

import com.esmc.gestionformation.entities.Validation;
import com.esmc.gestionformation.feign.AvrRestClient;
import com.esmc.gestionformation.repositories.ValidationRepository;
import com.esmc.gestionformation.serviceinterfaces.ValidationInterface;
import com.esmc.models.Extrant;
import com.esmc.models.ExtrantInputv2;
import com.esmc.models.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ValidataionService implements ValidationInterface {

    @Autowired
    private ValidationRepository validationRepository;

    @Autowired
    private AvrRestClient avrRestClient;
    @Override
    public Validation addValidation(Validation data) {
        //Formatter<Extrant> extrantFormatter = avrRestClient.create(data);

        return validationRepository.save(data);
    }

    @Override
    public Validation getValidation(Long idAgr, Long idDetailAgrFranchise, Long idPoste) {

        Validation validation = validationRepository.getStatus(idAgr,idDetailAgrFranchise,idPoste);

        if(validation == null){
           //* System.out.println(validation.getIdDetailAgr());
            return null;
        }
        return validation;
    }
}

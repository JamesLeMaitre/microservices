package com.esmc.gestionformation.serviceinterfaces;


import com.esmc.gestionformation.entities.Validation;
import com.esmc.models.ExtrantInputv2;

public interface ValidationInterface {
    public Validation addValidation(Validation data);

    Validation getValidation(Long idAgr, Long idDetailAgrFranchise, Long idPoste);

}

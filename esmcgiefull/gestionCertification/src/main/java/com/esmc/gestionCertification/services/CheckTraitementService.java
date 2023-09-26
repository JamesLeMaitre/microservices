package com.esmc.gestionCertification.services;

import com.esmc.gestionCertification.entities.CheckTraitement;
import com.esmc.input.CheckTraitementInput;

public interface CheckTraitementService {

    CheckTraitement saveStatus(CheckTraitementInput data);

    CheckTraitement update(Long idx,Long idy,Long idz,Long idt);

    CheckTraitement updateError(Long idx,Long idy,Long idz,Long idt);

    CheckTraitement getCurrent(Long idx,Long idy,Long idz,Long idt);
}

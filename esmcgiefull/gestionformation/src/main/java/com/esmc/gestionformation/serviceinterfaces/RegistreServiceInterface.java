package com.esmc.gestionformation.serviceinterfaces;

import com.esmc.gestionformation.entities.Registre;
import com.esmc.gestionformation.inputs.CodeInput;
import com.esmc.gestionformation.inputs.ObjectInput;

import java.util.List;

public interface RegistreServiceInterface {

    Registre initRegistre(String data);

    Registre updateCheckOut(ObjectInput data);

    Registre checkExist(CodeInput data);

    Registre updateRegistre(String data);

    List<Registre> allRegistreFull(Long formation);

    List<Registre> allRegistreFullFranchise(Long idDetAgrFranchiser);
}

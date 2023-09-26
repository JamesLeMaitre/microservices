package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.Lettre;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LettreServicesInterfaces {
    @Transactional
    public Lettre addLettre(Lettre lettre);
    @Transactional
    public Lettre updateLettre(Long id, Lettre lettre);

    public  void deleteLettre (Long id);

    @Transactional
    public List<Lettre> listLettres();


}

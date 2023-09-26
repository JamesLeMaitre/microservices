package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.entities.Pays;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PaysInt {
    List<Pays> getPays();

    Pays getPaysById(Long id);

    Pays addPays(Pays pays);

    Pays updatePays( Pays pays);

    void deletePays(Long paysId);

    public  List<Pays> listZoneMonnetaire(Long id);

    int count();

    int getCountByZoneMonetaire(Long id);

    int getCountByContinent(Long id);

    int getCountAll();

    int getCountFreePaysByZoneMonnetaire(Long idFranchise);

    int getCountBuyPaysByZoneMonnetaire(Long idFranchise);
}

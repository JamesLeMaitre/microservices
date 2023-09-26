package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.entities.ZoneMonnetaire;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ZoneMonnetaireInt {
    List<ZoneMonnetaire> getZoneMonnetaire();

    ZoneMonnetaire getZoneMonnetaireById(Long id);

    ZoneMonnetaire addZoneMonnetaire(ZoneMonnetaire zoneMonnetaire);

    ZoneMonnetaire updateZoneMonnetaire( ZoneMonnetaire zoneMonnetaire);

    void deleteZoneMonnetaire(Long zoneMonnetaireId);

    public  List<ZoneMonnetaire> listContinent(Long id);

    int countPaysByZoneMonetaire(Long id);

    String countPaysByWord();

    int getCountByContinent(Long id);

    int getCountAll();

    int getCountFreeZoneMonnetaireByContinent(Long idFranchise);

    int getCountBuyZoneMonnetaireByContinent(Long idFranchise);
}

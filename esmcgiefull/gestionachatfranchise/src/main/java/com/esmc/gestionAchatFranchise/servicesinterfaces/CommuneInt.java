package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.entities.Commune;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommuneInt {
    List<Commune> getCommune();

    Commune getCommuneById(Long id);

    Commune addCommune(Commune commune);

    Commune updateCommune( Commune commune);

    void deleteCommune(Long communeId);

    public  List<Commune> listPrefecture(Long id);

    int getCountAll();

    int getCountFreeCommuneByPrefecture(Long idFranchise);

    int getCountBuyCommuneByPrefecture(Long idFranchise);
}

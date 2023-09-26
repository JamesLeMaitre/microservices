package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.entities.Prefecture;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PrefectureInt {
    List<Prefecture> getPrefecture();

    Prefecture getPrefectureById(Long id);

    Prefecture addPrefecture(Prefecture prefecture);

    Prefecture updatePrefecture( Prefecture prefecture);

    void deletePrefecture(Long prefectureId);

    public List<Prefecture> listRegion(Long id);

    int getCountAll();

    int getCountFreePrefectureByRegion(Long idFranchise);

    int getCountBuyPrefectureByRegion(Long idFranchise);
}

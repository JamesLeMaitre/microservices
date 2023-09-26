package com.esmc.gestionAchatFranchise.servicesinterfaces;


import com.esmc.gestionAchatFranchise.entities.Region;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RegionInt {
    List<Region> getRegion();

    Region getRegionById(Long id);

    Region addRegion(Region region);

    Region updateRegion( Region region);

    void deleteRegion(Long regionId);

    public  List<Region> listPays(Long id);

    int getCountAll();

    int getCountFreeRegionByPays(Long idFranchise);

    int getCountBuyRegionByPays(Long idFranchise);
}

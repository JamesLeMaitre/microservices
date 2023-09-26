package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.Region;
import com.esmc.gestionAchatFranchise.repositories.RegionRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.RegionInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RegionImp implements RegionInt {
    @Autowired
    private RegionRepository regionRep;

    //  public RegionImp(RegionRepository regionRep) {
    //    this.regionRep = regionRep;
    //  }

    @Override
    public List<Region> getRegion() {
        return   regionRep.findAll();    }

    @Override
    public Region getRegionById(Long id) {
        return regionRep.findById(id).orElse(null);    }

    @Override
    public Region addRegion(Region region) {
        region.setDateCreate(new Date());
        region.setDateUpdate(new Date());
        return regionRep.save(region);    }

    @Override
    public Region updateRegion( Region region) {
        return regionRep.save(region);
    }

    @Override
    public void deleteRegion(@PathVariable Long regionId) {
        regionRep.deleteById(regionId);
    }

    @Override
    public List<Region> listPays(Long id) {
        return regionRep.findRegionByPays(id);
    }

    @Override
    public int getCountAll() {
        return (int) regionRep.count();
    }

    @Override
    public int getCountFreeRegionByPays(Long idFranchise) {
        return regionRep.getCountFreeRegionByPays(idFranchise);
    }

    @Override
    public int getCountBuyRegionByPays(Long idFranchise) {
        return regionRep.getCountBuyRegionByPays(idFranchise);
    }

}

package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.Prefecture;
import com.esmc.gestionAchatFranchise.repositories.PrefectureRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.PrefectureInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PrefectureImp implements PrefectureInt {
    @Autowired
    private PrefectureRepository prefectureRep;

    @Override
    public List<Prefecture> getPrefecture() {
        return  prefectureRep.findAll();
    }

    @Override
    public Prefecture getPrefectureById(Long id) {
        return prefectureRep.findById(id).orElse(null);
    }

    @Override
    public Prefecture addPrefecture(Prefecture prefecture) {
        prefecture.setDateCreate(new Date());
        prefecture.setDateUpdate(new Date());
        return prefectureRep.save(prefecture);
    }

    @Override
    public Prefecture updatePrefecture( Prefecture prefecture) {
        return prefectureRep.save(prefecture);
    }

    @Override
    public void deletePrefecture(@PathVariable Long prefectureId) {
        prefectureRep.deleteById(prefectureId);
    }

    @Override
    public List<Prefecture> listRegion(Long id) {
        return prefectureRep.findPrefectureByRegion(id);
    }

    @Override
    public int getCountAll() {
        return (int) prefectureRep.count();
    }

    @Override
    public int getCountFreePrefectureByRegion(Long idFranchise) {
        return prefectureRep.getCountFreePrefectureByRegion(idFranchise) ;
    }

    @Override
    public int getCountBuyPrefectureByRegion(Long idFranchise) {
        return prefectureRep.getCountBuyPrefectureByRegion(idFranchise) ;
    }


}

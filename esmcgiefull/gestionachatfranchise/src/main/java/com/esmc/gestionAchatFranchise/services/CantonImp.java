package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.Canton;
import com.esmc.gestionAchatFranchise.repositories.CantonRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.CantonInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CantonImp implements CantonInt {
    @Autowired
    private CantonRepository cantonRep;

    @Override
    public List<Canton> getCanton() {
        return  cantonRep.findAll();
    }

    @Override
    public Canton getCantonById(Long id) {
        return cantonRep.findById(id).orElse(null);
    }

    @Override
    public Canton addCanton(Canton canton) {

        return cantonRep.save(canton);
    }

    @Override
    public Canton updateCanton(Long id, Canton canton) {
        Canton data = cantonRep.findById(id).orElse(null);
        data.setCommune(canton.getCommune());
        data.setId(canton.getId());
        data.setLibelle(canton.getLibelle());
        data.setIsBuy(canton.getIsBuy());
        data.setPartners(canton.getPartners());
        data.setIdDetailAgr(canton.getIdDetailAgr());
        data.setStatusVente(canton.isStatusVente());
        data.setDateBuy(canton.getDateBuy());
        return cantonRep.save(canton);
    }

    @Override
    public void deleteCanton(@PathVariable Long cantonId) {
        cantonRep.deleteById(cantonId);
    }

    @Override
    public List<Canton> listCommune(Long id) {
        return cantonRep.findCantonByCommune(id);
    }

    @Override
    public int getCountAll() {
        return (int) cantonRep.count();
    }

    @Override
    public int getCountFreeCantonByCommune(Long idFranchise) {
        return cantonRep.getCountFreeCantonByCommune(idFranchise);
    }

    @Override
    public int getCountBuyCantonByCommune(Long idFranchise) {
        return cantonRep.getCountBuyCantonByCommune(idFranchise);
    }

}

package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import com.esmc.gestionAchatFranchise.repositories.AgenceOddRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.AgenceOddInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class AgenceOddImp implements AgenceOddInt {
    @Autowired
    private  AgenceOddRepository agenceOddRep;

    @Override
    public List<AgenceOdd> getAgenceOdd() {
        return agenceOddRep.listAgenceOddByNumber();
    }

    @Override
    public AgenceOdd getAgenceOddById(Long id) {
        return agenceOddRep.findById(id).get();    }

    @Override
    public AgenceOdd addAgenceOdd(AgenceOdd agenceOdd) {
        agenceOdd.setDateCreate(new Date());
        agenceOdd.setDateUpdate(new Date());
        return agenceOddRep.save(agenceOdd);
    }

    @Override
    public AgenceOdd updateAgenceOdd( AgenceOdd agenceOdd) {
        return agenceOddRep.save(agenceOdd);
    }

    @Override
    public void deleteAgenceOdd(@PathVariable Long agenceOddId) {
        agenceOddRep.deleteById(agenceOddId);

    }
}

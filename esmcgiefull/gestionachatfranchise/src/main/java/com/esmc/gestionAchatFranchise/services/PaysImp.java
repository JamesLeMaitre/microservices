package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.Pays;
import com.esmc.gestionAchatFranchise.repositories.PaysRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.PaysInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import utiles.UseFullFunctions;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class PaysImp implements PaysInt {

    private final UseFullFunctions useFullFunctions = new UseFullFunctions();
    @Autowired
    private PaysRepository paysRep;

    @Override
    public List<Pays> getPays() {
        return   paysRep.findAll();    }

    @Override
    public Pays getPaysById(Long id) {
        return paysRep.findById(id).orElse(null);
    }

    @Override
    public Pays addPays(Pays pays) {

        pays.setDateCreate(new Date());
        pays.setDateUpdate(new Date());
        pays.setPhoto(useFullFunctions.getUploadImageByBase64(pays.getPhoto()));
        return paysRep.save(pays);
    }

    @Override
    public Pays updatePays( Pays pays) {
        pays.setPhoto(useFullFunctions.getUploadImageByBase64(pays.getPhoto()));
        pays.setDateUpdate(new Date());
        return paysRep.save(pays);
    }

    @Override
    public void deletePays(@PathVariable Long paysId) {
        paysRep.deleteById(paysId);
    }

    @Override
    public List<Pays> listZoneMonnetaire(Long id) {
        return paysRep.findPaysByZoneMonnetaire(id);
    }

    @Override
    public int count() {
        return (int) paysRep.count();
    }

    @Override
    public int getCountByZoneMonetaire(Long id) {
        return paysRep.getCountByZoneMonetaire(id);
    }

    @Override
    public int getCountByContinent(Long id) {
        return paysRep.getCountByContinent(id);
    }

    @Override
    public int getCountAll() {
        return (int) paysRep.count();
    }

    @Override
    public int getCountFreePaysByZoneMonnetaire(Long idFranchise) {
        return paysRep.getCountFreePaysByZoneMonnetaire(idFranchise);
    }

    @Override
    public int getCountBuyPaysByZoneMonnetaire(Long idFranchise) {
        return paysRep.getCountBuyPaysByZoneMonnetaire(idFranchise);
    }
}

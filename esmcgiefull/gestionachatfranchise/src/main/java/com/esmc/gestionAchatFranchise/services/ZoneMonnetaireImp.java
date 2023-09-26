package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.ZoneMonnetaire;
import com.esmc.gestionAchatFranchise.repositories.ZoneMonetaireRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.ZoneMonnetaireInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ZoneMonnetaireImp implements ZoneMonnetaireInt {
    @Autowired
    private ZoneMonetaireRepository zoneMonetaireRep;

    @Override
    public List<ZoneMonnetaire> getZoneMonnetaire() {
        return    zoneMonetaireRep.findAll();
    }

    @Override
    public ZoneMonnetaire getZoneMonnetaireById(Long id) {
        return zoneMonetaireRep.findById(id).orElse(null);    }

    @Override
    public ZoneMonnetaire addZoneMonnetaire(ZoneMonnetaire zoneMonnetaire) {
        zoneMonnetaire.setDateCreate(new Date());
        zoneMonnetaire.setDateUpdate(new Date());
        return zoneMonetaireRep.save(zoneMonnetaire);
    }

    @Override
    public ZoneMonnetaire updateZoneMonnetaire( ZoneMonnetaire zoneMonnetaire) {
        return zoneMonetaireRep.save(zoneMonnetaire);
    }

    @Override
    public void deleteZoneMonnetaire(@PathVariable Long zoneMonnetaireId) {
        zoneMonetaireRep.deleteById(zoneMonnetaireId);

    }

    @Override
    public List<ZoneMonnetaire> listContinent(Long id) {
        return zoneMonetaireRep.findZoneMonnetaireByContinent(id);
    }

    @Override
    public int countPaysByZoneMonetaire(Long id) {
        return zoneMonetaireRep.countPaysByZoneMonetaire(id);
    }

    @Override
    public String countPaysByWord() {
        return null;
    }

    @Override
    public int getCountByContinent(Long id) {
        return (int) zoneMonetaireRep.getCountByContinent(id);
    }

    @Override
    public int getCountAll() {
        return (int) zoneMonetaireRep.count();
    }

    @Override
    public int getCountFreeZoneMonnetaireByContinent(Long idFranchise) {
        return zoneMonetaireRep.getCountFreeZoneMonnetaireByContinent(idFranchise);
    }

    @Override
    public int getCountBuyZoneMonnetaireByContinent(Long idFranchise) {
        return zoneMonetaireRep.getCountBuyZoneMonnetaireByContinent(idFranchise);
    }


    public int countPaysContinents(Long id) {
        return 1;
    }

    public int count() {
        return 1;
    }
}

package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.RegistreCommande;
import com.esmc.gestionAvr.repositories.RegistreCommandeRepository;
import com.esmc.gestionAvr.servicesInterfaces.RegistreCommandeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RegistreCommandeService implements RegistreCommandeInterface {

    @Autowired
    public RegistreCommandeRepository registreCommandeRepository;


    @Override
    public RegistreCommande addRegistreCommande(RegistreCommande r) {
        r.setDateCreate(new Date());
        r.setDateUpdate(new Date());
        return registreCommandeRepository.save(r);
    }

    @Override
    public RegistreCommande getRegistreCommandeId(Long id) {
        return registreCommandeRepository.findById(id).orElse(null);
    }

    @Override
    public RegistreCommande updateRegistreCommande(Long i,RegistreCommande r) {
        r.setDateUpdate(new Date());
        return registreCommandeRepository.save(r);
    }

    @Override
    public void deleteRegistreCommande(Long id) {
        registreCommandeRepository.deleteById(id);
    }

    @Override
    public List<RegistreCommande> listRegistreCommande() {
        return registreCommandeRepository.findAll();
    }

    @Override
    public List<RegistreCommande> ListRegistreCommandByDetailSMAvrreference(String reference) {
        return registreCommandeRepository.ListRegistreCommandByDetailSMAvrreference(reference);
    }

    @Override
    public List<RegistreCommande> ListRegistreCommandByDetailSMAvrId(Long id) {
        return registreCommandeRepository.ListRegistreCommandByDetailSMAvrId(id);
    }

    @Override
    public List<RegistreCommande> listRegistreCommandeByDetailSMAvrIdAndType(Long id, String reference) {
        return registreCommandeRepository.listRegistreCommandeByDetailSMAvrIdAndType(id, reference);
    }

    /*@Override
    public List<RegistreCommande> ListRegistreCommandByDetailSMAvrarticle(String article) {
        return registreCommandeRepository.ListRegistreCommandByDetailSMAvrarticle(article);
    }*/
}

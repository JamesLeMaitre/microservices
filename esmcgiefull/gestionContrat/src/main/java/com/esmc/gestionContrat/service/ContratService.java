package com.esmc.gestionContrat.service;

import com.esmc.gestionContrat.entities.Contrat;
import com.esmc.gestionContrat.entities.TypeContrat;
import com.esmc.gestionContrat.feign.DetailsAgrRestClient;
import com.esmc.gestionContrat.feign.KsuRestClient;
import com.esmc.gestionContrat.repositories.ContratRepository;
import com.esmc.gestionContrat.repositories.TypeContratRepository;
import com.esmc.gestionContrat.serviceInterfaces.ContratServiceInterface;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Ksu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ContratService implements ContratServiceInterface {
    @Autowired
    private ContratRepository contratRepository;
    @Autowired
    private  KsuRestClient ksuRestClient;
    @Autowired
    private DetailsAgrRestClient detailsAgrRestClient;
    @Autowired
    private TypeContratRepository typeContratRepository;


    @Override
    public Contrat addContrat(Contrat c) {
        c.setDateEntreVigueur(new Date());
        c.setDateFin(new Date());
        return contratRepository.save(c);
    }

    @Override
    public Contrat updateContrat(Contrat c,Long id) {
        Contrat contrat = contratRepository.findById(id).orElse(null);
       assert contrat != null;

       contrat.setContactFournisseur(c.getContactFournisseur());
       contrat.setMontantGlobalBps(c.getMontantGlobalBps());
       contrat.setMontantBci(c.getMontantBci());
       contrat.setTypeContrat(c.getTypeContrat());
      contrat.setEtatContrat(c.isEtatContrat());
       return contratRepository.save(c);
    }

    @Override
    @Transactional
    public void deleteContrat(Long id) {
        contratRepository.deleteById(id);
    }

    @Override
    public List<Contrat> listContrat() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat saveWithDetailsAgr(Contrat c, Long idDetailsAgr, String code) {
        DetailsAgr detailsAgr = detailsAgrRestClient.getDetailAgrById(idDetailsAgr);

        Ksu ksu = ksuRestClient.getById(detailsAgr.getKsu());

        if (detailsAgr == null){
            return null;
        } else {
            TypeContrat typeContrat = typeContratRepository.findByCode(code);
            assert  typeContrat != null;
            c.setBoitePostale(ksu.getBoitePostale());
            c.setProfession(ksu.getProfessionActuelle());
            c.setNomRepresentant(ksu.getNom() +" "+ ksu.getPrenom());
            c.setDomaineEntreprise(ksu.getSecteur().getLibelle());
            c.setNomPersonneMorale(ksu.getRaisonSocial());
            c.setSiegeSocial(ksu.getAdresse());
            c.setTelephone(ksu.getTelephone());
            c.setNomEntreprise(ksu.getRaisonSocial());
            c.setDateEntreVigueur(new Date());
            c.setDateFin(new Date());
            c.setTypeContrat(typeContrat);
            c.setDetailAgr(detailsAgr.getId());
            return  contratRepository.save(c);
        }

    }

    @Override
    public Contrat getById(Long id) {
        return contratRepository.findById(id).orElse(null);
    }

    @Override
    public Ksu getKsu(Long id) {
        Ksu ksu = ksuRestClient.getById(id);
        return ksu;
    }

}

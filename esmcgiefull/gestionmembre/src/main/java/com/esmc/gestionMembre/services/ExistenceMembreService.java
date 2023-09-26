package com.esmc.gestionMembre.services;

import com.esmc.gestionMembre.entities.*;
import com.esmc.gestionMembre.repositories.*;
import com.esmc.gestionMembre.serviceInterfaces.ExistenceMembreServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExistenceMembreService implements ExistenceMembreServiceInterface  {


    @Autowired
    public AncienMembreRepository ancienMembreRepository;
    @Autowired
    public MembreMoraleRepository membreMoraleRepository;
    @Autowired
    public MembreRepository membreRepository;
    @Autowired
    public PhysiqueRepository physiqueRepository;

    @Autowired
    public MoraleRepository moraleRepository;


    @Override
    public Physique RedemarrepersonnePhysique(String code) {
        return physiqueRepository.RedemarrepersonnePhysique(code);
    }

    @Override
    public List<Physique> listPhysique(String searchWords) {
        return physiqueRepository.listRedemarePP(searchWords);
    }

    @Override
    public Page<Physique> pageListPhysique(int offset, int pagesize) {
        return physiqueRepository.findAll(PageRequest.of(offset, pagesize));
    }

    @Override
    public Morale RedemarrepersonneMorale(String code) throws NullPointerException{
        return moraleRepository.RedemarrepersonneMorale(code);
    }

    @Override
    public List<Morale> listMorale(String searchWords) {
        return moraleRepository.listRedemarePM(searchWords);
    }

    @Override
    public Page<Morale> pageListMorale(int offset, int pagesize) {
        Page<Morale> pa = moraleRepository.findAll(PageRequest.of(offset, pagesize));
        return pa;
    }

    @Override
    public Page<Membre> pageListMembre(int offset, int pagesize) {
        Page<Membre> pa = membreRepository.findAll(PageRequest.of(offset, pagesize));
        return pa;
    }

    @Override
    public AncienMembre MCNPancienMembre(String code) {
        return ancienMembreRepository.MCNPancienMembre(code);
    }

    @Override
    public List<AncienMembre> listAncienMembrePP(String searchWords) {
        return ancienMembreRepository.listAncienMembrePP(searchWords);
    }

    @Override
    public Page<AncienMembre> pageListAncienMembrePP(int offset, int pageSize) {
        Page<AncienMembre> pa = ancienMembreRepository.findByAncienMembrepPP(PageRequest.of(offset, pageSize));
        return pa;
    }

    @Override
    public List<AncienMembre> listAncienMembrePM(String searchWord) {
        return ancienMembreRepository.listAncienMembrePM(searchWord);
    }

    @Override
    public Page<AncienMembre> pageListAncienMembrePm(int offset, int pageSize) {
        Page<AncienMembre> page = ancienMembreRepository.findByAncienMembrepPM(PageRequest.of(offset, pageSize));
        return page;
    }

    @Override
    public Membre ESMCSARLUmembreFondateurMembre(String code) {
      /*  System.out.println("============data================");
        System.out.println(membreRepository.ESMCSARLUmembreFondateurMembre(code));*/
        return membreRepository.ESMCSARLUmembreFondateurMembre(code);
    }

    @Override
    public List<Membre> listMembre(String searchWords) {
        //String pattern  = "MM-dd-yyyy";
          String data = String.valueOf(formatNumber(searchWords));
        return membreRepository.listMembrePP(searchWords);
    }

    @Override
    public MembreMorale ESMCSARLUmembreFondateurMembreMorale(String code) {
        return membreMoraleRepository.ESMCSARLUmembreFondateurMembreMorale(code);
    }

    @Override
    public List<MembreMorale> listMembreMorale(String searchWords) {
        return membreMoraleRepository.listEsmcMorale(searchWords);
    }

    @Override
    public Page<MembreMorale> pageListMembreMorale(int offset, int pageSize) {
        Page<MembreMorale> page = membreMoraleRepository.findAll(PageRequest.of(offset, pageSize));
        return page;
    }

    @Override
    public StringBuilder formatNumber(String words){
        //String sample = "krishna64";
        char[] chars = words.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : chars){
            if(Character.isDigit(c)){
                sb.append(c);
            }
        }
        System.out.println(sb);
        return sb;
    }



}


package com.esmc.gestionMembre.services.recherche;


import com.esmc.gestionMembre.entities.Physique;
import com.esmc.gestionMembre.repositories.PhysiqueRepository;
import com.esmc.gestionMembre.serviceInterfaces.recherche.PhysiqueServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PhysiqueServiceImpl implements PhysiqueServiceInterface {


    /**
     * @author Amemorte99
     */
    @Autowired
    private PhysiqueRepository physiqueRepository;



    @Override
    public Page<Physique> pageListSearchPhysiqueByDateLieu(String search,int offset, int pageSize) {
        return physiqueRepository.searchPhysiqueByAttributeDateLieuNaiss(search,PageRequest.of(offset, pageSize));
    }



    @Override
    public Physique  getReDeMaRePhysiqueByNom (String nom,String prenom) {
        return physiqueRepository.findReDeMaRePhysiqueByNom(nom,prenom);
    }

    @Override
    public Physique  getReDeMaRePhysiqueByCode(String code) {
        return physiqueRepository.findReDeMaRePhysiqueByNom(code);
    }
}

package com.esmc.gestionKsu.services;

import com.esmc.gestionKsu.entities.Banque;
import com.esmc.gestionKsu.repositories.BanqueRepository;
import com.esmc.gestionKsu.serviceinterfaces.BanqueServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Banque entity service
 * @author katoh <katohdavid@gmail.com>
 */
@Service
public class BanqueService implements BanqueServiceInterface {

    @Autowired
    private BanqueRepository banqueRepository;


    public boolean isPresent(Long id){
        Optional<Banque> opbanque=banqueRepository.findById(id);
        if (opbanque.isPresent())
            return true;
        return false;
    }

    /**
     * Check if bank exist by libelle
     * @param libelle
     * @return
     */
    public boolean isPresent(String libelle){
        Optional<Banque> opbanque=banqueRepository.findByLibelle(libelle);
        if (opbanque.isPresent())
            return true;
        return false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param b
     * @return
     */
    @Override
    public Banque addBanque(Banque b) {
        return banqueRepository.save(b);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Banque getBanqueById(Long id) {
        return banqueRepository.findById(id).orElse(null);
    }

    /**
     * @param id
     * @param b
     * @return
     */
    @Override
    public void updateBanque(Banque b) {
        Banque bq=banqueRepository.findById(b.getId()).orElse(null);

        //Check if this bank is already in database by libelle
        if (isPresent(b.getLibelle()))
            bq=banqueRepository.findById(b.getId()).orElse(null);

        //setting data
        bq.setLibelle(b.getLibelle());
        bq.setDateUpdate(new Date());

        //update bank
        b=banqueRepository.save(bq);
    }

    /**
     * @param id
     */
    @Override
    public void deleteBanque(Long id) {
        banqueRepository.deleteById(id);
    }

    /**
     * @return
     */
    @Override
    public List<Banque> listBanque() {
        return banqueRepository.findAll();
    }
}

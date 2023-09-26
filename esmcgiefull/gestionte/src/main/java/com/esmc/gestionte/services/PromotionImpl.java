package com.esmc.gestionte.services;

import com.esmc.gestionte.entities.Promotion;
import com.esmc.gestionte.exceptions.Exceptions;
import com.esmc.gestionte.repositories.PromotionRepository;
import com.esmc.gestionte.serviceinterface.PromotionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PromotionImpl implements PromotionInterface {

    @Autowired
    PromotionRepository promotionRepository;
    @Override
    public List<Promotion> getPromotion() {
        return promotionRepository.findAll();
    }

    @Override
    public void addNewPromotion(Promotion promotion) throws Exceptions {

        promotionRepository.save(promotion);

    }

    @Override
    public void deletePromotion(Long id) throws Exceptions {
        boolean exists = promotionRepository.existsById(id);
        if(!exists)
            throw  new Exceptions(Exceptions.alertGeneralException("agr dont l'id "+id+"n'existe pas "));
        promotionRepository.deleteById(id);

    }

    @Override
    public void updatePromotion(Long id, Promotion promotion) throws Exceptions {

        if(!isPresent(promotion.getId()))
            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'existe pas"));
        Promotion promotion1=getById(promotion.getId());

        promotion1.setLibelle(promotion.getLibelle());
        promotion.setDateDebut(new Date());
        promotion.setDateFin(new Date());

        //update supportMarchandEnchage
        promotion=promotionRepository.save(promotion1);

        if (promotion==null){
            throw new Exceptions(Exceptions.alertGeneralException("modification reussi"));
        }

    }

    @Override
    public Promotion getById(Long id) throws Exceptions {
        if(!isPresent(id))
            throw new Exceptions(Exceptions.alertGeneralException("l'identifiant n'esiste pas"));
        Optional<Promotion> opad=promotionRepository.findById(id);
        return  opad.get();
    }

    @Override
    public Double montantPromotion(Date debut, Date defin, double montantBci) {

        Promotion p = promotionRepository.getPromotionByDateDebutAndDateFin(debut, defin);
        double montantBan = 0.0;

        if (p != null) {
            System.out.println("Promotion : "+p.getLibelle());
            montantBan =  (montantBci/700000) * p.getMontantPromo();
            System.out.println("MontantBan : "+montantBan);
        }

        return montantBan;
    }

    @Override
    public Promotion getPromotionByDateDebutAndDateFin(Date dateDebut, Date dateFin) {
        return promotionRepository.getPromotionByDateDebutAndDateFin(dateDebut, dateFin);
    }

    @Override
    public Promotion getPromotionTrue() {
        return promotionRepository.getPromotionByEtatTrue();
    }

    public boolean isPresent(Long id){
        Optional<Promotion> opab=promotionRepository.findById(id);
        if (opab.isPresent())
            return true;
        return false;
    }


//  BAn En BCi

    @Override
    public Double montantPromotionBCI(Date debut, Date defin, double montantBan) {

        Promotion p = promotionRepository.getPromotionByDateDebutAndDateFin(debut, defin);
        double montantBci = 0.0;

        if (p != null) {
            System.out.println("Promotion : "+p.getLibelle());
            montantBci =  (montantBan*700000) / p.getMontantPromo();
            System.out.println("MontantBci : "+montantBan);
        }

        return montantBci;
    }
}

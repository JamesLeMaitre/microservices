package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.PromotionVague;
import com.esmc.gestionAvr.entities.Vague;
import com.esmc.gestionAvr.entities.VagueHistory;
import com.esmc.gestionAvr.inputs.VagueInput;
import com.esmc.gestionAvr.repositories.PromotionRepository;
import com.esmc.gestionAvr.repositories.VagueHistoryRepo;
import com.esmc.gestionAvr.repositories.VagueRepo;
import com.esmc.gestionAvr.servicesInterfaces.VagueServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VagueService implements VagueServiceInterface {

    @Autowired
    private VagueRepo vagueRepository;

    @Autowired
    private VagueHistoryRepo vagueHistoryRepo;

    @Autowired
    private PromotionRepository promotionRepository;


    @Override
    public List<Vague> getAll() {
        return vagueRepository.findAll();
    }

    @Override
    public Vague getVagueById(Long id) {
        return vagueRepository.getVagueById(id);
    }

    @Override
    public Vague activateVagueById(Long id) {
        //desactivate all vague -- to be upgrade
        List<Vague> listVagues = vagueRepository.findAll();
        for(Vague v: listVagues){
            v.setStatus(false);
            vagueRepository.save(v);
        }
        Vague vague = vagueRepository.getVagueById(id);
        vague.setStatus(true);
        vagueRepository.save(vague);
        //get last vague history
        VagueHistory lastVagueHistory = vagueHistoryRepo.findTopByOrderByIdDesc();
        if(lastVagueHistory != null){
            lastVagueHistory.setEndDate(new Date());
            vagueHistoryRepo.save(lastVagueHistory);
        }
        //create new vague history
        VagueHistory vagueHistory = new VagueHistory();
        vagueHistory.setVague(vague);
        vagueHistoryRepo.save(vagueHistory);
        return vague;
    }

    @Override
    public Vague getVagueByLabel(String label) {
        return  vagueRepository.getVagueByLabel(label);
    }

    @Override
    public Vague createVague(VagueInput data) {
        Vague vague = new Vague();
        vague.setLabel(data.getLabel());
        vague.setLimitAmount(data.getLimitAmount());
        if(data.getDescription() != null){
            vague.setDescription(data.getDescription());
        }

        if(data.getNumerator() != null){
            vague.setNumerator(data.getNumerator());
        }

        if(data.getDenominator() != null){
            vague.setDenominator(data.getDenominator());
        }
        if(data.getNextVague()!=null){
            Vague nextVague = vagueRepository.getVagueById(data.getNextVague());
            if(nextVague != null){
                vague.setNextVague(nextVague.getId());
            }
        }

        if(data.getIdPromotion()!=null){
            PromotionVague promotionVague = promotionRepository.findById(data.getIdPromotion()).get();
            if(promotionVague != null){
                vague.setIdPromotion(promotionVague.getId());
            }
        }


        return vagueRepository.save(vague);
    }

    @Override
    public Vague updateVague(Long id, VagueInput data) {
        Vague vague = vagueRepository.getVagueById(id);
        if(data.getLabel() != null){
            vague.setLabel(data.getLabel());
        }

        if(data.getLimitAmount() != null){
            vague.setLimitAmount(data.getLimitAmount());
        }
        if(data.getDescription() != null){
            vague.setDescription(data.getDescription());
        }

        if(data.getNextVague()!=null){
            Vague nextVague = vagueRepository.getVagueById(data.getNextVague());
            if(nextVague != null){
                vague.setNextVague(nextVague.getId());
            }
            vague.setNextVague(nextVague.getId());
        }

        if(data.getNumerator() != null){
            vague.setNumerator(data.getNumerator());
        }

        if(data.getDenominator() != null){
            vague.setDenominator(data.getDenominator());
        }

        if(data.getIdPromotion()!=null){
            PromotionVague promotionVague = promotionRepository.findById(data.getIdPromotion()).get();
            if(promotionVague != null){
                vague.setIdPromotion(promotionVague.getId());
            }
        }

        return vagueRepository.save(vague);
    }

    @Override
    public Vague getActiveVague() {
        return this.getActiveVagueInfo();
    }

    @Override
    public Vague getActiveVagueInfo(){
        return vagueRepository.getActiveVague(PageRequest.of(0,1));
    }

    @Override
    public String convertBanBci(Double amount) {
        Vague vague = this.getActiveVagueInfo();
        if(vague == null){
            return "0";
        }
        Double numerator = vague.getNumerator();
        Double denominator = vague.getDenominator();

        if(numerator <=0.0 || denominator <=0.0){
            return "0";
        }

        return ((amount*denominator)/numerator)+"";
    }

    @Override
    public String convertBciBan(Double amount) {
        Vague vague = this.getActiveVagueInfo();
        if(vague == null){
            return "0";
        }
        Double numerator = vague.getNumerator();
        Double denominator = vague.getDenominator();

        if(numerator <=0.0 && denominator <=0.0){
            return "0";
        }

        return ((amount*numerator)/denominator)+"";
    }

    @Override
    public List<Vague> getListByPromotion(Long id) {
        return vagueRepository.getByPromotion(id);
    }


    @Override
    public double convertBanBciV1(Double amount) {
        Vague vague = this.getActiveVagueInfo();
        if (vague == null) {
            return 0;
        }
        Double numerator = vague.getNumerator();
        Double denominator = vague.getDenominator();

        if (numerator <= 0.0 || denominator <= 0.0) {
            return 0.0;
        }
        return ((amount * denominator) / numerator);
    }

    @Override
    public double convertBciBanV1(Double amount) {
        Vague vague = this.getActiveVagueInfo();
        if (vague == null) {
            return 0.0;
        }
        Double numerator = vague.getNumerator();
        Double denominator = vague.getDenominator();

        if (numerator <= 0.0 && denominator <= 0.0) {
            return 0.0;
        }
        return (amount * numerator) / denominator;
    }

    @Override
    public double convertBanBciVague0(Double amount) {

        return (amount * 700000) / 21875;
    }

}


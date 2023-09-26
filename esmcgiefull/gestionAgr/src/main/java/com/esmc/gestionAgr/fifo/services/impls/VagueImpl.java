package com.esmc.gestionAgr.fifo.services.impls;

import com.esmc.gestionAgr.fifo.entities.PromotionVague;
import com.esmc.gestionAgr.fifo.entities.Vague;
import com.esmc.gestionAgr.fifo.repositories.PromotionVagueRepository;
import com.esmc.gestionAgr.fifo.repositories.VagueRepository;
import com.esmc.gestionAgr.fifo.services.VagueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class VagueImpl implements VagueService {
    private final VagueRepository vagueRepository;
    private final PromotionVagueRepository promotionRepository;

    @Override
    public Vague getActiveVagueInfo() {
        return vagueRepository.getActiveVague(PageRequest.of(0, 1));
    }

    @Override
    public double convertBanBci(Double amount) {
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
    public double convertBciBan(Double amount) {
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
    public Vague createVague(Vague data) {
        Vague vague = new Vague();
        vague.setLabel(data.getLabel());
        vague.setLimitAmount(data.getLimitAmount());
        if (data.getDescription() != null) {
            vague.setDescription(data.getDescription());
        }
        if (data.getNumerator() != null) {
            vague.setNumerator(data.getNumerator());
        }

        if (data.getDenominator() != null) {
            vague.setDenominator(data.getDenominator());
        }
        if (data.getNextVague() != null) {
            Vague nextVague = vagueRepository.getVagueById(data.getNextVague());
            if (nextVague != null) {
                vague.setNextVague(nextVague.getId());
            }
        }
        if (data.getIdPromotion() != null) {
            PromotionVague promotionVague = promotionRepository.findById(data.getIdPromotion()).get();
            vague.setIdPromotion(promotionVague.getId());
        }
        return vagueRepository.save(vague);
    }

    @Override
    public Optional<Vague> getVagueId(Long id) {
        return vagueRepository.findAll().stream()
                .filter(v -> v.getId().equals(id) && v.getStatus())
                .findFirst();
    }

}

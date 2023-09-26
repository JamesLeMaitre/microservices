package com.esmc.gestionAgr.fifo.services.impls;

import com.esmc.gestionAgr.fifo.entities.PromotionVague;
import com.esmc.gestionAgr.fifo.repositories.PromotionVagueRepository;
import com.esmc.gestionAgr.fifo.services.PromotionVagueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PromotionVagueImpl implements PromotionVagueService {
    private final PromotionVagueRepository promotionVagueRepository;

    @Override
    public PromotionVague create(PromotionVague data) {
        PromotionVague element = new PromotionVague();
        if (data.getLabel() != null) {
            element.setLabel(data.getLabel());
        }
        if (data.getDescription() != null) {
            element.setDescription(data.getDescription());
        }

        return promotionVagueRepository.save(element);
    }
}

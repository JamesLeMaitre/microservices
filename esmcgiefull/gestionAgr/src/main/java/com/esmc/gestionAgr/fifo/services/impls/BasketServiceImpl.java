package com.esmc.gestionAgr.fifo.services.impls;


import com.esmc.gestionAgr.fifo.entities.Fifo;
import com.esmc.gestionAgr.fifo.entities.Tour;
import com.esmc.gestionAgr.fifo.repositories.TourRepository;
import com.esmc.gestionAgr.fifo.services.BasketService;
import com.esmc.gestionAgr.fifo.utils.enums.KsuType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final TourRepository repository;


    @Override
    public void addOrRemoveBasket(Fifo fifo, boolean add) throws Exception {
        Optional<Tour> basket = repository.findByName("Basket").stream().findFirst();
        if (basket.isEmpty()) {
            throw new Exception("Basket don't exist");
        } else {
            if (fifo.getKsuType() == KsuType.SELLER) {
                if (add) {
                    basket.get().getSellers().add(fifo);
                } else {
                    basket.get().getSellers().removeIf(s -> s.getKsu().equals(fifo.getKsu()));
                }
            } else if (fifo.getKsuType() == KsuType.BUYER) {
                if (add) {
                    basket.get().getBuyers().add(fifo);
                } else {
                    basket.get().getBuyers().removeIf(b -> b.getKsu().equals(fifo.getKsu()));
                }
            }
            repository.saveAndFlush(basket.get());
        }
    }

}

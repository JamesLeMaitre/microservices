package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.Fifo;
import com.esmc.gestionAvr.entities.Tour;
import com.esmc.gestionAvr.repositories.TourRepository;
import com.esmc.gestionAvr.utils.enums.KsuType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BasketServiceImpl implements BasketService{
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
                    basket.get().getSellers().removeIf(s -> s.getKsu().getId().equals(fifo.getKsu().getId()));
                }
            } else if (fifo.getKsuType() == KsuType.BUYER) {
                if (add) {

                    basket.get().getBuyers().add(fifo);
                } else {
                    basket.get().getBuyers().removeIf(s -> s.getKsu().getId().equals(fifo.getKsu().getId()));
                }
            }
            repository.saveAndFlush(basket.get());
        }
    }
}

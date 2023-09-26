package com.esmc.gestionAgr.fifo.services.impls;

import com.esmc.gestionAgr.fifo.entities.Fifo;
import com.esmc.gestionAgr.fifo.entities.Tour;
import com.esmc.gestionAgr.fifo.repositories.TourRepository;
import com.esmc.gestionAgr.fifo.services.TourService;
import com.esmc.gestionAgr.fifo.utils.JavaUtils;
import com.esmc.gestionAgr.fifo.utils.enums.KsuType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Slf4j
@Service
@AllArgsConstructor
public class TourServiceImpl implements TourService {

    private final JavaUtils utils;
    private final TourRepository repository;

    @Override
    public void addTour(Fifo fifo) throws Exception {
        utils.findTour("Tour").peek(tour -> JavaUtils.checkTourAdd(tour, fifo))
                .map(repository::saveAndFlush).findFirst()
                .orElseThrow(() -> new Exception("Tour doesn't exist !"));
    }

    @Override
    public Tour getTour(String name) {
        return repository.findAll().stream()
                .filter(tour -> tour.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Tour getTourv1(String name) {
        return repository.findAll().get(0);
    }


    /**
     * @param fifo
     * @return
     * @throws Exception
     */


    @Override
    public void removeTour(Fifo fifo) throws Exception {
        Optional<Tour> tour = repository.findByName("Tour").stream().findFirst();
        if (tour.isEmpty()) {
            throw new Exception("Tour n'existe pas");
        } else {
            if (fifo.getKsuType() == KsuType.SELLER) {
                tour.get().getSellers().removeIf(s -> s.getKsu().getCodeKsu().equals(fifo.getKsu().getCodeKsu()));
            } else if (fifo.getKsuType() == KsuType.BUYER) {
                tour.get().getBuyers().removeIf(b -> b.getKsu().getCodeKsu().equals(fifo.getKsu().getCodeKsu()));
            }
            repository.saveAndFlush(tour.get());
        }
    }


}

package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.Fifo;
import com.esmc.gestionAvr.entities.Tour;
import com.esmc.gestionAvr.repositories.TourRepository;
import com.esmc.gestionAvr.servicesInterfaces.TourInterface;
import com.esmc.gestionAvr.utils.JavaUtils;
import com.esmc.gestionAvr.utils.enums.KsuType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TourService implements TourInterface {
    private final TourRepository tourRepository;

    @Autowired
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
                tour.get().getSellers().removeIf(s -> s.getKsu().equals(fifo.getKsu()));
            } else if (fifo.getKsuType() == KsuType.BUYER) {
                tour.get().getBuyers().removeIf(b -> b.getKsu().equals(fifo.getKsu()));
            }
            repository.saveAndFlush(tour.get());
        }
    }


//    public void manageTour(Fifo fifo) throws Exception {
//        if (fifo.getAmount() == 0) {
//            throw new Exception("Amount Null");
//        } else {
//            if (fifo.getKsuType() == KsuType.SELLER) {
//                log.info("Amount : {}", fifo.getAmount());
//                double secondMaxValueBan = vagueService.convertBciBan(fifo.getAmount());
//                double difference = MAX_TOUR_SELLER_BCI - fifo.getAmount();
//                if (MAX_TOUR_SELLER_BCI > fifo.getAmount()) {
//                    fifo.setDefaultAmountSecondType(fifo.getAmount());
//                    fifo.setDefaultAmountFirstType(secondMaxValueBan);
//                    fifo.setActionAmountFirstType(secondMaxValueBan);
//                    fifo.setActionAmountSecondType(fifo.getAmount());
//                    fifoRepository.save(fifo);
//                    tourService.addTour(fifo);
//                } else {
//                    fifo.setDefaultAmountSecondType(MAX_TOUR_SELLER_BCI);
//                    fifo.setDefaultAmountFirstType(vagueService.convertBciBan(MAX_TOUR_SELLER_BCI));
//                    fifo.setActionAmountFirstType(vagueService.convertBciBan(MAX_TOUR_SELLER_BCI));
//                    fifo.setActionAmountSecondType(MAX_TOUR_SELLER_BCI);
//                    fifo.setAmount(MAX_TOUR_SELLER_BCI);
//                    fifoRepository.save(fifo);
//                    tourService.addTour(fifo);
//                    Fifo fifo1 = new Fifo();
//                    fifo1.setKsu(fifo.getKsu());
//                    fifo1.setDefaultAmountSecondType(-1 * difference);
//                    fifo1.setDefaultAmountFirstType(-1 * vagueService.convertBciBan(difference));
//                    fifo1.setActionAmountFirstType(-1 * vagueService.convertBciBan(difference));
//                    fifo1.setActionAmountSecondType(-1 * difference);
//                    fifo1.setKsuType(fifo.getKsuType());
//                    fifo1.setAmount(-1 * difference);
//                    fifoRepository.save(fifo1);
//                    basketService.addOrRemoveBasket(fifo1, true);
//                }
//            } else if (fifo.getKsuType() == KsuType.BUYER) {
//                double secondMaxValueBci = vagueService.convertBanBci(fifo.getAmount());
//                double difference = MAX_TOUR_BUYER_BAN - fifo.getAmount();
//                if (MAX_TOUR_BUYER_BAN > fifo.getAmount()) {
//                    fifo.setDefaultAmountSecondType(secondMaxValueBci);
//                    fifo.setDefaultAmountFirstType(fifo.getAmount());
//                    fifo.setActionAmountFirstType(fifo.getAmount());
//                    fifo.setActionAmountSecondType(secondMaxValueBci);
//                    fifoRepository.save(fifo);
//                    tourService.addTour(fifo);
//                } else {
//                    fifo.setDefaultAmountSecondType(vagueService.convertBciBan(MAX_TOUR_BUYER_BAN));
//                    fifo.setDefaultAmountFirstType(MAX_TOUR_BUYER_BAN);
//                    fifo.setActionAmountFirstType(MAX_TOUR_BUYER_BAN);
//                    fifo.setActionAmountSecondType(vagueService.convertBciBan(MAX_TOUR_BUYER_BAN));
//                    fifo.setAmount(MAX_TOUR_BUYER_BAN);
//                    fifoRepository.save(fifo);
//                    tourService.addTour(fifo);
//                    Fifo fifo1 = new Fifo();
//                    fifo1.setKsu(fifo.getKsu());
//                    fifo1.setDefaultAmountSecondType(-1 * vagueService.convertBciBan(difference));
//                    fifo1.setDefaultAmountFirstType(-1 * difference);
//                    fifo1.setActionAmountFirstType(-1 * difference);
//                    fifo1.setActionAmountSecondType(-1 * vagueService.convertBciBan(difference));
//                    fifo1.setKsuType(fifo.getKsuType());
//                    fifo1.setAmount(-1 * difference);
//                    fifoRepository.save(fifo1);
//                    basketService.addOrRemoveBasket(fifo1, true);
//
//                }
//            }
//        }
//    }

}

package com.esmc.gestionAgr.fifo.services;


import com.esmc.gestionAgr.fifo.entities.Fifo;
import com.esmc.gestionAgr.fifo.entities.Tour;

public interface TourToBasketService {

    // Set Amount to the tour if max set to basket
    void manageTour(Fifo fifo) throws Exception;

    Tour getAll(String name);

    void preProcessing() throws Exception;


    // I want to check date
    void setBasketDate();
}

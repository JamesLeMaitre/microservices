package com.esmc.gestionAgr.fifo.services;


import com.esmc.gestionAgr.fifo.entities.Fifo;
import com.esmc.gestionAgr.fifo.entities.Tour;

public interface TourService {
    void addTour(Fifo fifo) throws Exception;

    Tour getTour(String name);

    Tour getTourv1(String name);

    void removeTour(Fifo fifo) throws Exception;


}

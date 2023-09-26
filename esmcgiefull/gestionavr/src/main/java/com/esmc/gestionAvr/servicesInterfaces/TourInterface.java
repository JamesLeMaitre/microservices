package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.Fifo;
import com.esmc.gestionAvr.entities.Tour;

public interface TourInterface {


    void addTour(Fifo fifo) throws Exception;

    Tour getTour(String name);

    void removeTour(Fifo fifo) throws Exception;

}

package com.esmc.gestionAgr.fifo.services;


import com.esmc.gestionAgr.fifo.entities.Fifo;

public interface BasketService {
    void addOrRemoveBasket(Fifo fifo, boolean add) throws Exception;

}

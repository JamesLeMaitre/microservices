package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.Fifo;

public interface BasketService  {
    void addOrRemoveBasket(Fifo fifo, boolean add) throws Exception;
}

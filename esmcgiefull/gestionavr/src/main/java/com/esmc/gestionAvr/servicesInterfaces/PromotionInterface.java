package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.PromotionVague;

import java.util.List;

public interface PromotionInterface {
    List<PromotionVague> getAll();

    PromotionVague getById(Long id);

    PromotionVague create(PromotionVague data);

    PromotionVague update(Long id, PromotionVague data);

    void delete(Long id);
}

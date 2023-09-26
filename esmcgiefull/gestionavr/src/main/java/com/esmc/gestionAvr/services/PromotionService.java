package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.PromotionVague;
import com.esmc.gestionAvr.repositories.PromotionRepository;
import com.esmc.gestionAvr.servicesInterfaces.PromotionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService implements PromotionInterface {
    @Autowired
    private PromotionRepository repository;

    @Override
    public List<PromotionVague> getAll() {
        return repository.findAll();
    }

    @Override
    public PromotionVague getById(Long id) {
        return repository.findById(id).get();
    }


    @Override
    public PromotionVague create(PromotionVague data) {
        PromotionVague element = new PromotionVague();

        if(data.getLabel() != null){
            element.setLabel(data.getLabel());
        }

        if(data.getDescription() != null){
            element.setDescription(data.getDescription());
        }

        return repository.save(element);
    }

    @Override
    public PromotionVague update(Long id, PromotionVague data) {
        PromotionVague element = this.getById(id);

        if(data.getLabel() != null){
            element.setLabel(data.getLabel());
        }

        if(data.getDescription() != null){
            element.setDescription(data.getDescription());
        }

        return repository.save(element);
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}


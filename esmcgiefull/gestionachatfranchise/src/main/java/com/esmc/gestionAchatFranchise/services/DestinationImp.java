package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.Destination;
import com.esmc.gestionAchatFranchise.repositories.DestinationRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.DestinationInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DestinationImp implements DestinationInt {
    @Autowired
    private DestinationRepository destinationRep;



    @Override
    public List<Destination> getDestination() {
        return  destinationRep.findAll();
    }

    @Override
    public Destination getDestinationById(Long id) {
        return destinationRep.findById(id).get();    }

    @Override
    public Destination addDestination(Destination destination) {
        destination.setDateCreate(new Date());
        destination.setDateUpdate(new Date());
        return destinationRep.save(destination);
    }

    @Override
    public Destination updateDestination(Destination destination) {
        return destinationRep.save(destination);
    }

    @Override
    public void deleteDestination(@PathVariable Long destinationId) {
        destinationRep.deleteById(destinationId);
    }
}

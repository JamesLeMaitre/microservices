package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.entities.Destination;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DestinationInt {
    List<Destination> getDestination();

    Destination getDestinationById(Long id);

    Destination addDestination(Destination destination);

    Destination updateDestination( Destination destination);

    void deleteDestination(Long destinationId);
}

package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.entities.DetailDestination;

import java.util.List;

public interface DetailDestinationInt {
    List<DetailDestination> getDetailDestination();

    DetailDestination getDetailDestinationById(Long id);

    DetailDestination addDetailDestination(DetailDestination detailDestination);

    DetailDestination updateDetailDestination( DetailDestination detailDestination);

    void deleteDetailDestination(Long detailDestinationId);

    public  List<DetailDestination> listDestination(Long id);
}

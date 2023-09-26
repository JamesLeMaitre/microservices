package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.DetailDestination;
import com.esmc.gestionAchatFranchise.repositories.DetailDestinationRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.DetailDestinationInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DetailDestinationImp implements DetailDestinationInt {
    @Autowired
    private DetailDestinationRepository detailDestinationRep;


    @Override
    public List<DetailDestination> getDetailDestination() {
        return  detailDestinationRep.findAll();
    }

    @Override
    public DetailDestination getDetailDestinationById(Long id) {
        return detailDestinationRep.findById(id).get();
    }

    @Override
    public DetailDestination addDetailDestination(DetailDestination detailDestination) {
        detailDestination.setDateCreate(new Date());
        detailDestination.setDateUpdate(new Date());
        return detailDestinationRep.save(detailDestination);
    }

    @Override
    public DetailDestination updateDetailDestination( DetailDestination detailDestination) {
        return detailDestinationRep.save(detailDestination);
    }

    @Override
    public void deleteDetailDestination(@PathVariable Long detailDestinationId) {
        detailDestinationRep.deleteById(detailDestinationId);
    }

    @Override
    public List<DetailDestination> listDestination(Long id) {
        return detailDestinationRep.findDetailDestinationByDestination(id);
    }
}

package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.entities.CentreFranchise;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CentreFranchiseInt {
    List<CentreFranchise> getCentreFranchise();

    CentreFranchise getCentreFranchiseById(Long id);

    CentreFranchise addCentreFranchise(CentreFranchise centreFranchise);

    CentreFranchise updateCentreFranchise(Long id, CentreFranchise centreFranchise);

    void deleteCentreFranchise(Long centreFranchiseId);
}

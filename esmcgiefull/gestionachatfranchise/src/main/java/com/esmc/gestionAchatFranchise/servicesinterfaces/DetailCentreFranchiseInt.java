package com.esmc.gestionAchatFranchise.servicesinterfaces;
import com.esmc.gestionAchatFranchise.entities.DetailCentreFranchise;

import java.util.List;

public interface DetailCentreFranchiseInt {

    List<DetailCentreFranchise> getDetailCentreFranchise();

    DetailCentreFranchise getDetailCentreFranchiseById(Long id);

    DetailCentreFranchise addDetailCentreFranchise(DetailCentreFranchise detailCentreFranchise);

    DetailCentreFranchise updateDetailCentreFranchise( DetailCentreFranchise detailCentreFranchise);

    void deleteDetailCentreFranchise(Long detailCentreFranchiseId);

    public  List<DetailCentreFranchise> listCentreFranchise(Long id);

    public  List<DetailCentreFranchise> listCanton(Long id);

    public  List<DetailCentreFranchise> listCentreFranchiseAndCanton(Long id, Long id2);

}

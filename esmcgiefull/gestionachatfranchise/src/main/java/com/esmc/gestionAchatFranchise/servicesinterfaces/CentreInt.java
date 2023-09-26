package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.entities.Centre;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CentreInt {


        List<Centre> getCentre();

        Centre getCentreById(Long id);

        Centre addCentre(Centre centre);

        Centre updateCentre( Centre centre);

        void deleteCentre(Long centreId);

        public  List<Centre> listCentreFranchiseCentre(Long id);

}

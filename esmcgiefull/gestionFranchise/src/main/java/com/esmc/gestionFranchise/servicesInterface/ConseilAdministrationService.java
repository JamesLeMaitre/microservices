package com.esmc.gestionFranchise.servicesInterface;


import com.esmc.gestionFranchise.entities.ConseilAdministration;

import java.util.List;

public interface ConseilAdministrationService {
    List<ConseilAdministration>getallConseilAdmin();
    List<ConseilAdministration> getConseilAdministrationbyFranchise(Long id);
    ConseilAdministration ajouterConseilAdministration(ConseilAdministration conseilAdministration);
    ConseilAdministration getConseilAdministrationbyId(Long id);
    void deleteConseilAdministration(Long id);

}

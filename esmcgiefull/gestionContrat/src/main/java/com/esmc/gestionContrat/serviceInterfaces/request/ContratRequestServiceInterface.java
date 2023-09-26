package com.esmc.gestionContrat.serviceInterfaces.request;

import com.esmc.gestionContrat.request.ContratRequest;
import com.esmc.gestionContrat.request.ContratRequestInput;
import com.esmc.models.Ksu;

import java.io.IOException;
import java.util.List;

public interface ContratRequestServiceInterface {
    ContratRequest save(Long idDetailsAgr,ContratRequest request);
    List<ContratRequest> getContrat();

    //ContratRequest create(Long idDetailsAgr, ContratRequest request) throws IOException;

    ContratRequest createv2(Long idDetailsAgr, ContratRequestInput request) throws IOException;

    Ksu ksu(Long id);
}

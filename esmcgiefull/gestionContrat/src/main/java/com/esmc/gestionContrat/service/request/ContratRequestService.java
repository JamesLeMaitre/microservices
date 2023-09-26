package com.esmc.gestionContrat.service.request;

import com.esmc.gestionContrat.feign.DetailsAgrRestClient;
import com.esmc.gestionContrat.feign.KsuRestClient;
import com.esmc.gestionContrat.repositories.request.ContratRequestRepository;
import com.esmc.gestionContrat.request.ContratRequest;
import com.esmc.gestionContrat.request.ContratRequestInput;
import com.esmc.gestionContrat.serviceInterfaces.request.ContratRequestServiceInterface;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Ksu;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ContratRequestService implements ContratRequestServiceInterface {
    private ContratRequestRepository requestRepository;
    private DetailsAgrRestClient detailsAgrRestClient;
    private KsuRestClient ksuRestClient;


    @Override
    public ContratRequest save(Long idDetailsAgr, ContratRequest request) {
        DetailsAgr detailsAgr = detailsAgrRestClient.getDetailAgrById(idDetailsAgr);
        Ksu ksu = ksuRestClient.getById(detailsAgr.getKsu());
        //request.setStage(+1);
        request.setIdDetailAgr(ksu.getId());
        return requestRepository.save(request);

    }

    @Override
    public List<ContratRequest> getContrat() {
        return requestRepository.findAll();
    }

    public <T> T getFirstElement(List<T> listElements){
        T element = null;
        if(listElements.size() > 0){
            element = listElements.get(0);
        }

        return element;
    }


    @Override
    public ContratRequest createv2(Long idDetailsAgr, ContratRequestInput request) throws IOException {
        DetailsAgr detailsAgr = detailsAgrRestClient.getDetailAgrById(idDetailsAgr);
        Ksu ksu = ksuRestClient.getById(detailsAgr.getKsu());
        request.setIdDetailAgr(ksu.getId());

         ContratRequest contratRequest = getFirstElement(requestRepository.findByIdDetailsAgr(idDetailsAgr));
        //System.out.println(contratRequest);

        if(contratRequest == null){
            contratRequest = new ContratRequest();
            contratRequest.setIdDetailAgr(idDetailsAgr);
            contratRequest.setStage(request.getStage());

        }
        String [] data;
        if(contratRequest.getData().length > 0) {
            data = contratRequest.getData();
        }else{
            data = new String[10];
        }

        //set new values
        data[request.getStage()] = request.getData();
        contratRequest.setData(data);

        return requestRepository.save(contratRequest) ;
    }

    @Override
    public Ksu ksu(Long id) {
        DetailsAgr detailsAgr = detailsAgrRestClient.getDetailAgrById(id);
        return ksuRestClient.getById(detailsAgr.getKsu());
    }
}

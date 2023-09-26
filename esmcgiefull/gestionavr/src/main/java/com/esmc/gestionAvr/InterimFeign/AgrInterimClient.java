package com.esmc.gestionAvr.InterimFeign;

import com.esmc.gestionAvr.cronUtilities.DefaultClient;
import com.esmc.models.DetailsAgr;
import com.esmc.models.TerminalEchange;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Component
public class AgrInterimClient extends DefaultClient {
    public String defaultPath="AGR-SERVICE/";

    public DetailsAgr getDetailById(@PathVariable Long id){
        String url = String.format("api/detailsagrs/detail_agr/byId/%s",id);
        return  (DetailsAgr) this.GetRequest(defaultPath+url,new ParameterizedTypeReference<DetailsAgr>() {});
    }



}

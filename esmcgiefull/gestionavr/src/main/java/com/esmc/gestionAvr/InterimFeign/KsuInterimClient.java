package com.esmc.gestionAvr.InterimFeign;

import com.esmc.gestionAvr.cronUtilities.DefaultClient;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Ksu;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
@Component
public class KsuInterimClient extends DefaultClient {
    public String defaultPath="KSU-SERVICE/";

    public Ksu getById(@PathVariable Long id){
        String url = String.format("api/ksus/getById/%s",id);
        return  (Ksu) this.GetRequest(defaultPath+url,new ParameterizedTypeReference<Ksu>() {});
    }
}

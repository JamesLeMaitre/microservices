package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.Extrant;
import com.esmc.gestionAvr.entities.Media;
import com.esmc.gestionAvr.entities.MiddleWareExtrant;
import com.esmc.gestionAvr.inputs.MInput;
import net.minidev.json.JSONObject;

import java.util.List;

public interface MiddleWareExtrantInterface {
         public MiddleWareExtrant updateMedia(MInput dt);

         public List<MiddleWareExtrant> listIntrant(Long idintervant);

    List<MiddleWareExtrant> archivage(Long idMx);

    List<Extrant> getExtrantArchiver(Long idMx);

    List<Extrant> getByTour(Long idMx);
//
//         List<MiddleWareExtrant> archiverTour(Long id);
//         MiddleWareExtrant viderTour(Long id);
}

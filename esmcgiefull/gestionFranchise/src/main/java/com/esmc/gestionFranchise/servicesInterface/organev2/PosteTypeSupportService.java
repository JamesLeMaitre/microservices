package com.esmc.gestionFranchise.servicesInterface.organev2;

import com.esmc.gestionFranchise.entities.organev2.PosteTypeSupport;
import org.json.simple.JSONObject;


import java.util.List;

public interface PosteTypeSupportService {
    List<JSONObject> getAll();
    PosteTypeSupport  getPosteTypeSupportById(Long id);

    PosteTypeSupport create(PosteTypeSupport data, Long idTypeSupport, Long idPosteSender, Long idPosteReceiver);

    PosteTypeSupport  update(Long id, PosteTypeSupport data);

    PosteTypeSupport disable(Long id);

    PosteTypeSupport enable(Long id);

    void delete(Long id);

    List<JSONObject> getPosteTypeSupportByIntervenants(Long idSender, Long idReceiver, Long idTDEp);
}

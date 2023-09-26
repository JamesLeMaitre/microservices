package com.esmc.gestionFranchise.services.organev2;

import com.esmc.gestionFranchise.entities.TableDescriptionEp;
import com.esmc.gestionFranchise.entities.organev2.Poste;
import com.esmc.gestionFranchise.entities.organev2.PosteTypeSupport;
import com.esmc.gestionFranchise.entities.organev2.TypeSupport;
import com.esmc.gestionFranchise.repositories.TableDescriptionEpRepo;
import com.esmc.gestionFranchise.repositories.organev2.PosteRepo;
import com.esmc.gestionFranchise.repositories.organev2.PosteTypeSupportRepo;
import com.esmc.gestionFranchise.repositories.organev2.TypeSupportRepo;
import com.esmc.gestionFranchise.servicesInterface.organev2.PosteTypeSupportService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Service
@Transactional
public class PosteTypeSupportServiceImpl implements PosteTypeSupportService {
    @Autowired
    private PosteTypeSupportRepo ptRepo;
    @Autowired
    private PosteRepo posteRepo;
    @Autowired
    private TypeSupportRepo tprepo;
    @Autowired
    private TableDescriptionEpRepo  tRepo;

    @Override
    public List<JSONObject> getAll() {
        List<JSONObject> json = new ArrayList<>();
        List<PosteTypeSupport> posteTypeSupports = ptRepo.findAll();
        for(PosteTypeSupport p : posteTypeSupports){
            JSONObject formDetailsJson = new JSONObject();
            formDetailsJson.put("idPosteTS", p.getId());
            formDetailsJson.put("libelleTypeSupport", p.getTypeSupport().getLibelle());
            formDetailsJson.put("idSender", p.getSenderPoste().getId());
            formDetailsJson.put("libelleSender", p.getSenderPoste().getLibelle());
            formDetailsJson.put("idReceiver", p.getReceiverPoste().getId());
            formDetailsJson.put("libelleReceiver", p.getReceiverPoste().getLibelle());
            json.add(formDetailsJson);
        }
        return json;
    }

    @Override
    public PosteTypeSupport getPosteTypeSupportById(Long id) {
        return ptRepo.findById(id).orElse(null);
    }

    @Override
    public PosteTypeSupport create(PosteTypeSupport data, Long idTypeSupport, Long idPosteSender, Long idPosteReceiver) {
        Poste postesender =  posteRepo.findById(idPosteSender).orElse(null);
        Poste postereceiver =  posteRepo.findById(idPosteReceiver).orElse(null);

        TypeSupport typeSupport = tprepo.findById(idTypeSupport).orElse(null);
        data.setSenderPoste(postesender);
        data.setReceiverPoste(postereceiver);
        return ptRepo.save(data);
    }

    @Override
    public PosteTypeSupport update(Long id, PosteTypeSupport data) {
        PosteTypeSupport posteTypeSupport = new PosteTypeSupport();
        posteTypeSupport = ptRepo.findById(id).orElse(null);
        assert posteTypeSupport != null : "Id is null";
        posteTypeSupport.setId(data.getId());
        posteTypeSupport.setTypeSupport(data.getTypeSupport());
        posteTypeSupport.setReceiverPoste(data.getReceiverPoste());
        posteTypeSupport.setSenderPoste(data.getSenderPoste());
        posteTypeSupport.setStatus(data.isStatus());
        return ptRepo.save(posteTypeSupport);
    }

    @Override
    public PosteTypeSupport disable(Long id) {
        PosteTypeSupport typeSupport = ptRepo.findById(id).orElse(null);
        assert typeSupport != null;
        typeSupport.setStatus(false);
        return typeSupport;
    }

    @Override
    public PosteTypeSupport enable(Long id) {
        PosteTypeSupport typeSupport = ptRepo.findById(id).orElse(null);
        assert typeSupport != null;
        typeSupport.setStatus(true);
        return typeSupport;
    }

    @Override
    public void delete(Long id) {
        ptRepo.deleteById(id);
    }

    @Override
    public  List<JSONObject> getPosteTypeSupportByIntervenants(Long idReceiver, Long idSender, Long idTDEp) {
        Poste p1 = posteRepo.findById(idSender).orElse(null);
        Poste p2 = posteRepo.findById(idReceiver).orElse(null);
        TableDescriptionEp p3 = tRepo.findById(idTDEp).orElse(null);

        assert p2 != null:"ID Sender null";
        assert p1 != null: "ID Receiver null";
        assert p3 != null : "ID TDEp null";

        List<JSONObject> json = new ArrayList<>();
        List<PosteTypeSupport> posteTypeSupports = ptRepo.getAllSupportByPoste(idReceiver,idSender,idTDEp);
        for(PosteTypeSupport p : posteTypeSupports){
            JSONObject formDetailsJson = new JSONObject();
            formDetailsJson.put("id", p.getTypeSupport().getId());
            formDetailsJson.put("libelle", p.getTypeSupport().getLibelle());
            json.add(formDetailsJson);
        }
        return json;

    }
}

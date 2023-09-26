package com.esmc.gestionFranchise.services.organev2;



import com.esmc.gestionFranchise.datafomater.PosteJSON;

import com.esmc.gestionFranchise.entities.organev2.Poste;
import com.esmc.gestionFranchise.entities.organev2.Structure;
import com.esmc.gestionFranchise.inputs.PosteView;
import com.esmc.gestionFranchise.repositories.organev2.PosteRepo;
import com.esmc.gestionFranchise.repositories.organev2.StructureRepo;
import com.esmc.gestionFranchise.servicesInterface.organev2.PosteService;
import com.esmc.gestionFranchise.servicesInterface.organev2.StructureService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
public class PosteServiceImpl implements PosteService {


  @Autowired
    PosteRepo posteRepo;


  @Autowired
  StructureService parentService;

    @Autowired
    StructureRepo structureRepository;



    @Override
    public List<Poste> getAll() {
        return posteRepo.findAll();
    }

    @Override
    public Object getAllV2() {
        return posteRepo.getNewList();
    }

    @Override
    public Page<Poste> getAllv2(int off,int size) {
        Page<Poste> data = posteRepo.getAllNo(PageRequest.of(off,size));
       return data;
    }



    @Override
    public Poste getById(Long id) {
        return posteRepo.findById(id).orElse(null);
    }

    @Override
    public Poste create(Poste data) {

        Poste element=new Poste();
        if(data.getLibelle() != null){
            element.setLibelle(data.getLibelle());
        }

        if(data.getDescription() != null){
            element.setDescription(data.getDescription());
        }

        if(data.getStructure() != null){
            Structure parent = parentService.getById(data.getStructure().getId());
            element.setStructure(parent);
        }
        return posteRepo.save(data);
    }

    @Override
    public Poste update(Long id, Poste data) {

        Poste element=getById(id);

        if(data.getLibelle() != null){
            element.setLibelle(data.getLibelle());
        }

        if(data.getDescription() != null){
            element.setDescription(data.getDescription());
        }


        if(data.getStructure() != null){
            Structure parent = parentService.getById(data.getStructure().getId());
            element.setStructure(parent);
        }

        element.setLibelle(data.getLibelle());

        return posteRepo.save(element);


    }

    @Override
    public Poste disable(Long id) {
        Poste element = getById(id);
        element.setStatus(false);
        return posteRepo.save(element);
    }

    @Override
    public Poste enable(Long id) {
        Poste element = getById(id);
        element.setStatus(true);
        return posteRepo.save(element);
    }

    @Override
    public void delete(Long id) {
        posteRepo.deleteById(id);
    }

    @Override
    public List<Poste> postByIdTypePoste(Long id) {
        return posteRepo.findPostByIdTypePoste(id);
    }


    public void RecursiveloadPost(PosteJSON[] data, Poste poste) {
        for( PosteJSON childGeration_2:data){
            Poste poste2 = new Poste();
            poste2.setLibelle(childGeration_2.getLibelle());
            Structure structure2= structureRepository.findById(childGeration_2.getStructure()).orElse(null);
            poste2.setStructure(structure2);
            poste2.setPosteParent(poste);
            Poste savedPoste = posteRepo.save(poste2);//generation 3
            if(childGeration_2.getChildren().length >0){
                RecursiveloadPost(childGeration_2.getChildren(),savedPoste );
            }
        }
    }

/*
    public void RecursiveloadPost(PosteJSON[] data, Poste poste) {
        for( PosteJSON posteJSON:data){
            Poste poste2 = new Poste();
            poste2.setLibelle(posteJSON.getLibelle());
            Structure structure2= structureRepository.findById(posteJSON.getStructure()).orElse(null);
            poste2.setStructure(structure2);
            poste2.setPosteParent(poste);
            Poste savedPoste = posteRepo.save(poste2);//generation 3
            if(childGeration_2.getChildren().length >0){
                RecursiveloadPost(childGeration_2.getChildren(),savedPoste );
            }
        }
    }
*/

    @Override
    public void loadPost(PosteJSON[] data) {
        RecursiveloadPost(data, null);
    }

    @Override
    public List<Poste> getPosteByParent(Long id) {
        Poste poste = posteRepo.findById(id).orElse(null);
        assert poste != null: "ID null";
        return posteRepo.getPosteByParent(poste.getId());
    }

    @Override
    public List<Poste> getWithout(Long id) {
        return posteRepo.getWithoutId(id);
    }


  /*  public  void recursivePost( PosteJSON [] posteJSONS,Poste poste,Poste poste1){
        for (PosteJSON posteJSONc : posteJSONS){

            Poste po = new Poste();
            poste.setLibelle(posteJSONc.getLibelle());
            Structure structure= new Structure();
            structure.setId(posteJSONc.getStructure());
            po.setStructure(structure);
            if(poste1 != null){
                po.setId(poste1.getId());
            }
            po.setId(poste.getId());
            posteRepo.save(po);

        }

    }*/
}

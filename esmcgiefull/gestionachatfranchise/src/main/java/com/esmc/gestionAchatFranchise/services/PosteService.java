package com.esmc.gestionAchatFranchise.services;

import com.esmc.gestionAchatFranchise.entities.PosteFranchise;
import com.esmc.gestionAchatFranchise.repositories.PosteRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.PosteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utiles.UseFullFunctions;

import java.util.List;

@Service
public class PosteService implements PosteInterface {
    @Autowired
    private PosteRepository repository;


    private UseFullFunctions useFullFunctions = new UseFullFunctions();



    @Override
    public List<PosteFranchise> getAll() {
        return repository.findAll();
    }

    @Override
    public PosteFranchise getById(Long id) {
        return repository.findById(id).get();
    }


    @Override
    public PosteFranchise create(PosteFranchise data) {
        PosteFranchise element = new PosteFranchise();

        if(data.getLibelle() != null){
            element.setLibelle(data.getLibelle());
        }

        if(data.getDescription() != null){
            element.setDescription(data.getDescription());
        }

        if(data.getCode() != null){
            element.setCode(data.getCode());
        }

        if(data.getImage() != null){
            element.setImage( useFullFunctions.getUploadImageByBase64(data.getImage()));
        }

        return repository.save(element);
    }

    @Override
    public PosteFranchise update(Long id, PosteFranchise data) {
        PosteFranchise element = repository.findById(id).get();

        if(data.getLibelle() != null){
            element.setLibelle(data.getLibelle());
        }

        if(data.getDescription() != null){
            element.setDescription(data.getDescription());
        }

        if(data.getCode() != null){
            element.setCode(data.getCode());
        }

        if(data.getImage() != null){
            element.setImage( useFullFunctions.getUploadImageByBase64(data.getImage()));
        }

        return repository.save(element);
    }



    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public int getCountAll() {
        return (int) this.repository.count();
    }


}

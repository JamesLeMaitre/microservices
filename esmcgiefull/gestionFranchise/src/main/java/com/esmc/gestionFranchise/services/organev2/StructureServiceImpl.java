package com.esmc.gestionFranchise.services.organev2;


import com.esmc.gestionFranchise.datafomater.StrufctureJSON;
import com.esmc.gestionFranchise.entities.organev2.Structure;
import com.esmc.gestionFranchise.repositories.organev2.StructureRepo;
import com.esmc.gestionFranchise.servicesInterface.organev2.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StructureServiceImpl implements StructureService {

    @Autowired
    StructureRepo structureRepository;



    @Override
    public List<Structure> getAll() {

        return structureRepository.findAll();
    }

    @Override
    public Structure getById(Long id) {

        return structureRepository.findById(id).get();
    }

    @Override
    public Structure create(Structure data) {

        return structureRepository.save(data);


    }

    @Override
    public Structure update(Long id, Structure data) {

        Structure element=getById(id);

        if(data.getLibelle() != null){
            element.setLibelle(data.getLibelle());
        }

        element.setLibelle(data.getLibelle());

        return structureRepository.save(element);
    }

    @Override
    public Structure disable(Long id) {

        Structure element = getById(id);
        element.setStatus(false);
        return structureRepository.save(element);

    }

    @Override
    public Structure enable(Long id) {
        Structure element = getById(id);
        element.setStatus(true);
        return structureRepository.save(element);
    }

    @Override
    public void delete(Long id) {

        structureRepository.deleteById(id);

    }

    @Override
    public void loadResource(StrufctureJSON[] data) {
        for (StrufctureJSON s: data){
            Structure structure = new Structure();
            structure.setLibelle(s.getLibelle());
            structure.setNiveau(s.getNiveau());
            structureRepository.save(structure);
        }
    }

}

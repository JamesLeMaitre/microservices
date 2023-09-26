package com.esmc.gestionFranchise.services.organev2;

import com.esmc.gestionFranchise.entities.organev2.TypeSupport;
import com.esmc.gestionFranchise.repositories.organev2.TypeSupportRepo;
import com.esmc.gestionFranchise.servicesInterface.organev2.TypeSupportService;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TypeSupportServiceImpl implements TypeSupportService {

    @Autowired
    private  TypeSupportRepo typeSupportRepo;

    @Override
    public JSONObject getAll() {
      JSONObject obj = new JSONObject();
        obj.put("data",typeSupportRepo.getAllType());
        obj.values();
      return obj;
    }



    @Override
    public TypeSupport getTypeSupportById(Long id) {
        return typeSupportRepo.findById(id).orElse(null);
    }

    @Override
    public String  getLibelle(Long id) {
        return typeSupportRepo.getLibelle(id);
    }

    @Override
    public TypeSupport create(TypeSupport data) {
        return typeSupportRepo.save(data);
    }

    @Override
    public TypeSupport update(TypeSupport data,Long id) {
        TypeSupport typeSupport = new TypeSupport();
        typeSupport = typeSupportRepo.findById(id).orElse(null);
        typeSupport.setId(data.getId());
        typeSupport.setLibelle(data.getLibelle());
      /*  typeSupport.setTypeSupports(data.getTypeSupports());*/
        return typeSupportRepo.save(typeSupport);
    }

    @Override
    public TypeSupport disable(Long id) {
        return null;
    }

    @Override
    public TypeSupport enable(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        typeSupportRepo.deleteById(id);
    }
}

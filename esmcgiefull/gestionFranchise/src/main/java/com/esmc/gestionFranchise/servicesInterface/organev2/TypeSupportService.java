package com.esmc.gestionFranchise.servicesInterface.organev2;

import com.esmc.gestionFranchise.entities.organev2.TypeSupport;
import net.minidev.json.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public interface TypeSupportService {
    JSONObject getAll();

    TypeSupport  getTypeSupportById(Long id);

    String  getLibelle(Long id);

    TypeSupport  create(TypeSupport data);

    TypeSupport  update(TypeSupport data,Long id);

    TypeSupport disable(Long id);

    TypeSupport enable(Long id);

    void delete(Long id);
}

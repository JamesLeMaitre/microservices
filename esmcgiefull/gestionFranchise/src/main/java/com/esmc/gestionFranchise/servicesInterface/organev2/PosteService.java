package com.esmc.gestionFranchise.servicesInterface.organev2;



import com.esmc.gestionFranchise.datafomater.PosteJSON;
import com.esmc.gestionFranchise.datafomater.StrufctureJSON;
import com.esmc.gestionFranchise.entities.organev2.Poste;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Stream;

public interface PosteService {

     List<Poste> getAll();

    Object getAllV2();

    Page<Poste> getAllv2(int off, int size);

    Poste  getById(Long id);

    Poste  create(Poste data);
    Poste  update(Long id, Poste data);

    Poste disable(Long id);

    Poste enable(Long id);

    void delete(Long id);

   List<Poste>  postByIdTypePoste(Long id);

    void loadPost(PosteJSON[] data);

    List<Poste> getPosteByParent(Long id);

    List<Poste> getWithout(Long id);
}

package com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flbose;

import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseCible;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FlbOseCibleInterface {
    List<FlbOseCible> getAll();
    FlbOseCible  getById(Long id);

    FlbOseCible  create(FlbOseCible data);
    FlbOseCible  update(Long id, FlbOseCible data);

    FlbOseCible disable(Long id);

    FlbOseCible enable(Long id);

    void delete(Long id);

    List<FlbOseCible> getListByAgenceOddId(Long id);

    int getCountByAgenceOdd(Long id);

    int getCountAll();
}
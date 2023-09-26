package com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flboe;
;
import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChambre;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FlbOeChambreInterface {
    List<FlbOeChambre> getAll();
    FlbOeChambre  getById(Long id);

    FlbOeChambre  create(FlbOeChambre data);
    FlbOeChambre  update(Long id, FlbOeChambre data);

    FlbOeChambre disable(Long id);

    FlbOeChambre enable(Long id);

    int getCountAll();

    void delete(Long id);
}

package com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flbose;

import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseAgenceOdd;

import java.util.List;

public interface FlbOseAgenceOddInterface {
    List<FlbOseAgenceOdd> getAll();
    FlbOseAgenceOdd  getById(Long id);

    FlbOseAgenceOdd  create(FlbOseAgenceOdd data);
    FlbOseAgenceOdd  update(Long id, FlbOseAgenceOdd data);

    FlbOseAgenceOdd disable(Long id);

    FlbOseAgenceOdd enable(Long id);

    int getCountAll();

    void delete(Long id);
}

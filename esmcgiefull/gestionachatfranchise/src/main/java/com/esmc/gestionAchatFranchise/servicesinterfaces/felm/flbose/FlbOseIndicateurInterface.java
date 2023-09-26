package com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flbose;

import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseIndicateur;

import java.util.List;

public interface FlbOseIndicateurInterface {
    List<FlbOseIndicateur> getAll();
    FlbOseIndicateur  getById(Long id);

    FlbOseIndicateur  create(FlbOseIndicateur data);
    FlbOseIndicateur  update(Long id, FlbOseIndicateur data);

    FlbOseIndicateur disable(Long id);

    FlbOseIndicateur enable(Long id);

    void delete(Long id);

    List<FlbOseIndicateur> getListByAgenceOddId(Long id);

    int getCountByAgenceOdd(Long id);

    int getCountAll();
}

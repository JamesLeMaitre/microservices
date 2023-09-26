package com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flboe;

import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChaineDistribution;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FlbOeChaineDistributionInterface {
    List<FlbOeChaineDistribution> getAll();

    FlbOeChaineDistribution getById(Long id);

    FlbOeChaineDistribution create(FlbOeChaineDistribution data);

    FlbOeChaineDistribution update(Long id, FlbOeChaineDistribution data);

    FlbOeChaineDistribution disable(Long id);

    FlbOeChaineDistribution enable(Long id);

    void delete(Long id);

    List<FlbOeChaineDistribution> getListByChaineValeurId(Long id);

    int getCountByChaineValue(Long id);

    int getCountByChambre(Long id);

    int getCountAll();
}

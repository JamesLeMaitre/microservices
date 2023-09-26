package com.esmc.gestionAchatFranchise.servicesinterfaces.felm.fill;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineDistribution;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FillChaineDistributionInterface  {
    List<FillChaineDistribution> getAll();
    FillChaineDistribution  getById(Long id);

    FillChaineDistribution  create(FillChaineDistribution data);
    FillChaineDistribution  update(Long id, FillChaineDistribution data);

    FillChaineDistribution disable(Long id);

    FillChaineDistribution enable(Long id);

    void delete(Long id);

    List<FillChaineDistribution> getListByChaineValeurId(Long id);

    int getCountByChaineValue(Long id);

    int getCountByInsitution(Long id);

    int getCountAll();
}

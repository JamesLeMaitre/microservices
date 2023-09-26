package com.esmc.gestionAchatFranchise.servicesinterfaces.felm.fill;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineValeur;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FillChaineValeurInterface  {
    List<FillChaineValeur> getAll();
    FillChaineValeur  getById(Long id);

    FillChaineValeur  create(FillChaineValeur data);
    FillChaineValeur  update(Long id, FillChaineValeur data);

    int getCountByInstitution(Long id);

    List<FillChaineValeur> getListByInstitutionId(Long id);

    int getCountAll();

    FillChaineValeur disable(Long id);

    FillChaineValeur enable(Long id);

    void delete(Long id);

    int getNbDistributorByPartenId(Long id);

}

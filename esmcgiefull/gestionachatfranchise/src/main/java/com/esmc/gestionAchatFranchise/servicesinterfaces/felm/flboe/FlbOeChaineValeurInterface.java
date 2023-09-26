package com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flboe;

import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChaineValeur;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FlbOeChaineValeurInterface {
    List<FlbOeChaineValeur> getAll();
    FlbOeChaineValeur  getById(Long id);

    FlbOeChaineValeur  create(FlbOeChaineValeur data);
    FlbOeChaineValeur  update(Long id, FlbOeChaineValeur data);

    List<FlbOeChaineValeur> getListByChambreId(Long id);

    int getCountAll();

    FlbOeChaineValeur disable(Long id);

    FlbOeChaineValeur enable(Long id);

    void delete(Long id);


    int getCountByChambre(Long id);
}

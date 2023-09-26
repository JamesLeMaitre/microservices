package com.esmc.gestionKsu.serviceinterfaces;

import com.esmc.gestionKsu.entities.Banque;

import java.util.List;

public interface BanqueServiceInterface {

    public Banque addBanque(Banque b);

    public Banque getBanqueById(Long id);

    public void updateBanque(Banque b);

    public void deleteBanque(Long id);

    public List<Banque> listBanque();

}

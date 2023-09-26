package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.Panier;
import com.esmc.models.Ksu;

import java.util.List;

public interface PanierServiceInterface {

    public Panier addPanier(Panier panier);
    public Panier updatePanier(Long id, Panier panier);

    public void deletePanier(Long id);
    public List<Panier> getAllPanier();
    public List<Panier> panierDeKsu(Long id);
    public Double prixTotalPanier(Long id);

    public List<Ksu> validerCommande(Long id);

    public Panier findById(Panier p);

}

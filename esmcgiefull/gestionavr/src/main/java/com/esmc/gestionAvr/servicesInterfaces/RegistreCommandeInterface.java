package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.RegistreCommande;

import java.util.List;

public interface RegistreCommandeInterface {
    public RegistreCommande addRegistreCommande(RegistreCommande r);


    public  RegistreCommande getRegistreCommandeId(Long id);


    public RegistreCommande updateRegistreCommande(Long id, RegistreCommande r);

    public void deleteRegistreCommande (Long id);

    public List<RegistreCommande> listRegistreCommande();

     List<RegistreCommande> ListRegistreCommandByDetailSMAvrId(Long id);

    public List<RegistreCommande> ListRegistreCommandByDetailSMAvrreference(String reference);


    public List<RegistreCommande> listRegistreCommandeByDetailSMAvrIdAndType(Long id, String reference);

   /* public List<RegistreCommande> ListRegistreCommandByDetailSMAvrarticle(String article);*/
}

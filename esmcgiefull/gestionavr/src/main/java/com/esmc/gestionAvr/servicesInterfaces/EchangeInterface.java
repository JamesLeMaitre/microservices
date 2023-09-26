package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.Echange;

import java.util.List;

public interface EchangeInterface {
    public Echange addEchange(Echange echange);

    public void deleteEchange(Long id);

    public Echange updateEchange(Echange echange);

    public List<Echange> listEchange();

    public Echange getEchange(Long id);

    /*public List<Echange> getEchangeByTypeEchange(Long id);*/

    /*public void Commande(TypeEchange typeEchange, Echange echange, SMAvr smAvr, Avr avr);

    public void ConfirmationCommande();

    public void Réclammation();*/
}

package com.esmc.gestionContrat.serviceInterfaces;

import com.esmc.gestionContrat.entities.Porte;

import java.util.List;

public interface PorteServiceInterface {

    public Porte addPorte(Porte p);
     Porte updatePorte(Porte porte);
    public void deletePorte(Long id);
    public List<Porte> listPorte();
}

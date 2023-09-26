package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.DetailSupport;

import java.util.List;

public interface DetailSupportServiceInterface {

    public DetailSupport addDetailSupport(DetailSupport d);

    public DetailSupport updateDetailSmar(DetailSupport d);

    public void deleteDetailSupport(Long id);

    public List<DetailSupport> ListDetailSupport();

    public DetailSupport getDetailSupportById(Long id);
    
}

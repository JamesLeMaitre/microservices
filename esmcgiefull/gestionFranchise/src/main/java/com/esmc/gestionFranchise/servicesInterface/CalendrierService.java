package com.esmc.gestionFranchise.servicesInterface;

import com.esmc.gestionFranchise.entities.Calendrier;

import java.util.List;

public interface CalendrierService {
    List<Calendrier> getCalendrier();
    Calendrier ajouterCalendrier(Calendrier Calendrier);
    Calendrier getCalendrierbyId(Long id);
    void deleteCalendrier(Long id);
}

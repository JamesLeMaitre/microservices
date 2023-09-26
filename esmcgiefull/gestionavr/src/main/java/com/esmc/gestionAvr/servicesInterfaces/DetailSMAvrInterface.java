package com.esmc.gestionAvr.servicesInterfaces;

import com.esmc.gestionAvr.entities.DetailSMAvr;

import java.util.List;

public interface DetailSMAvrInterface {
    public DetailSMAvr addDetailSmavr(DetailSMAvr d);

    public DetailSMAvr updateDetailSmar(DetailSMAvr d);

    public void deleteDetailSMAvr(Long id);

    public List<DetailSMAvr> ListDetailSMAvr();

    public List<DetailSMAvr> ListDetailSMAvrBySMAvr(Long id);

    public List<DetailSMAvr> ListDetailSMAvrByAvr(Long id);

}

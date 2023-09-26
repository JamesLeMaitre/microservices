package com.esmc.gestionAvr.servicesInterfaces;


import com.esmc.gestionAvr.entities.Vague;
import com.esmc.gestionAvr.inputs.VagueInput;

import java.util.List;

public interface VagueServiceInterface {

    List<Vague> getAll();

    Vague getVagueById(Long id);

    Vague activateVagueById(Long id);

    Vague getVagueByLabel(String Label);

    Vague createVague(VagueInput data);

    Vague updateVague(Long id, VagueInput data);

    Vague getActiveVague();

    Vague getActiveVagueInfo();

    String convertBanBci(Double amount);

    String convertBciBan(Double amount);

    List<Vague> getListByPromotion(Long id);


    double convertBanBciV1(Double amount);

    double convertBciBanV1(Double amount);

    double convertBanBciVague0(Double amount);
}
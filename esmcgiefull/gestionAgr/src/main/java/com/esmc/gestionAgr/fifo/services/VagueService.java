package com.esmc.gestionAgr.fifo.services;


import com.esmc.gestionAgr.fifo.entities.Vague;

import java.util.Optional;

public interface VagueService {
    Vague getActiveVagueInfo();

    double convertBanBci(Double amount);

    double convertBciBan(Double amount);

    Vague createVague(Vague data);

    Optional<Vague> getVagueId(Long id);
}

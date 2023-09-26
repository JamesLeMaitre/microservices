package com.esmc.gestionAgr.fifo.services;


import com.esmc.gestionAgr.fifo.entities.Ksu;

import java.util.Optional;

public interface KsuService {

    Optional<Ksu> getById(Long idKsu);
}

package com.esmc.gestionAgr.fifo.services.impls;

import com.esmc.gestionAgr.fifo.entities.Ksu;
import com.esmc.gestionAgr.fifo.repositories.KsuRepository;
import com.esmc.gestionAgr.fifo.services.KsuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class KsuImpl implements KsuService {
    private final KsuRepository ksuRepository;

    @Override
    public Optional<Ksu> getById(Long idKsu) {
        return ksuRepository.findFirstById(idKsu);
    }
}

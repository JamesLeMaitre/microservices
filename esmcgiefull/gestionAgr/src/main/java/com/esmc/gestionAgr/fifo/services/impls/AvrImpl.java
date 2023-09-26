package com.esmc.gestionAgr.fifo.services.impls;


import com.esmc.gestionAgr.fifo.entities.Avr;
import com.esmc.gestionAgr.fifo.repositories.AvrRepository;
import com.esmc.gestionAgr.fifo.services.AvrService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AvrImpl implements AvrService {
    private final AvrRepository avrRepository;

    @Override
    public List<Avr> listAvrByDetailAgrId(Long id) {
        return avrRepository.findAllByDetailAgr(id);
    }
}

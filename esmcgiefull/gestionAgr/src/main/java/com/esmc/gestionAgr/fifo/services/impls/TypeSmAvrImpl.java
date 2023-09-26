package com.esmc.gestionAgr.fifo.services.impls;

import com.esmc.gestionAgr.fifo.entities.TypeSmAvr;
import com.esmc.gestionAgr.fifo.repositories.TypeSmAvrRepository;
import com.esmc.gestionAgr.fifo.services.TypeSmAvrService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TypeSmAvrImpl implements TypeSmAvrService {
    private final TypeSmAvrRepository typeSmAvrRepository;

    @Override
    public TypeSmAvr getById(String code) {
        return typeSmAvrRepository.findByCode(code);
    }
}

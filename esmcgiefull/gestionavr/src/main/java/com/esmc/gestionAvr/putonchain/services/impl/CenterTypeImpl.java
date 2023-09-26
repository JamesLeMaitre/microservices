package com.esmc.gestionAvr.putonchain.services.impl;

import com.esmc.gestionAvr.putonchain.entities.CenterType;
import com.esmc.gestionAvr.putonchain.repositories.CenterTypeRepository;
import com.esmc.gestionAvr.putonchain.requests.CenterTypeRequest;
import com.esmc.gestionAvr.putonchain.services.CenterTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class CenterTypeImpl implements CenterTypeService {
    private final CenterTypeRepository centerTypeRepository;

    @Override
    public Mono<CenterType> addCenterType(Mono<CenterTypeRequest> requestMono) {
        return requestMono.flatMap(c -> Mono.just(centerTypeRepository.save(CenterType.builder().wording(c.getWording()).build())));
    }

    @Override
    public Flux<CenterType> getAllCenter() {
        return Flux.fromIterable(centerTypeRepository.findAll());
    }
}

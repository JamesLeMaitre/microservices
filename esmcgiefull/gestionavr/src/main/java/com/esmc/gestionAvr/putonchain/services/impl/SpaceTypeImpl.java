package com.esmc.gestionAvr.putonchain.services.impl;

import com.esmc.gestionAvr.putonchain.entities.SpaceType;
import com.esmc.gestionAvr.putonchain.repositories.SpaceTypeRepository;
import com.esmc.gestionAvr.putonchain.requests.SpaceTypeRequest;
import com.esmc.gestionAvr.putonchain.services.SpaceTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class SpaceTypeImpl implements SpaceTypeService {
    private final SpaceTypeRepository spaceTypeRepository;
    @Override
    public Mono<SpaceType> addSpaceType(Mono<SpaceTypeRequest> requestMono) {
        return requestMono.flatMap(c -> {
            return Mono.just(spaceTypeRepository.save(SpaceType.builder().wording(c.getWording()).build()));
        });
    }

    @Override
    public Flux<SpaceType> getAllSpace() {
        return Flux.fromIterable(spaceTypeRepository.findAll());
    }


}

package com.esmc.gestionAvr.putonchain.services;

import com.esmc.gestionAvr.putonchain.entities.SpaceType;
import com.esmc.gestionAvr.putonchain.requests.SpaceTypeRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SpaceTypeService {
    Mono<SpaceType> addSpaceType(Mono<SpaceTypeRequest> requestMono);

    Flux<SpaceType> getAllSpace();
}

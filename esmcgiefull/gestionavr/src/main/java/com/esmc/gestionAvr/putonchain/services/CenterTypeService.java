package com.esmc.gestionAvr.putonchain.services;

import com.esmc.gestionAvr.putonchain.entities.CenterType;
import com.esmc.gestionAvr.putonchain.requests.CenterTypeRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CenterTypeService {
    Mono<CenterType> addCenterType(Mono<CenterTypeRequest> requestMono);
    Flux<CenterType> getAllCenter();
}

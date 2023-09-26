package com.esmc.gestionAvr.putonchain.services;

import com.esmc.gestionAvr.putonchain.entities.UnitOfMeasure;
import com.esmc.gestionAvr.putonchain.exceptions.UnitOfMeasureException;
import com.esmc.gestionAvr.putonchain.requests.UnitOfMeasureRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UnitOfMeasureService {
    Mono<UnitOfMeasure> addUnitOfMeasure(UnitOfMeasureRequest request);

    Flux<UnitOfMeasure> getAllUnitOfMeasure();

    Mono<UnitOfMeasure> updateUnitOfMeasure(Long id, UnitOfMeasureRequest request) throws UnitOfMeasureException;

    Mono<UnitOfMeasure> getById(Long id) throws UnitOfMeasureException;

    Mono<Void> deleteUnitOfMeasure(Long id) throws UnitOfMeasureException;
}

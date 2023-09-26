package com.esmc.gestionAvr.putonchain.services.impl;

import com.esmc.gestionAvr.putonchain.entities.UnitOfMeasure;
import com.esmc.gestionAvr.putonchain.exceptions.UnitOfMeasureException;
import com.esmc.gestionAvr.putonchain.repositories.UnitOfMeasureRepository;
import com.esmc.gestionAvr.putonchain.requests.UnitOfMeasureRequest;
import com.esmc.gestionAvr.putonchain.services.UnitOfMeasureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class UnitOfMeasureImpl implements UnitOfMeasureService {
    private UnitOfMeasureRepository unitOfMesureRepository;

    @Override
    public Mono<UnitOfMeasure> addUnitOfMeasure(UnitOfMeasureRequest request) {
        UnitOfMeasure unitOfMeasure = UnitOfMeasure.builder().wording(request.getWording()).code(request.getCode()).build();
        return Mono.just(unitOfMesureRepository.save(unitOfMeasure));
    }


    @Override
    public Flux<UnitOfMeasure> getAllUnitOfMeasure() {
        return Flux.fromIterable(unitOfMesureRepository.findAll());
    }
    @Override
    public Mono<UnitOfMeasure> updateUnitOfMeasure(Long id, UnitOfMeasureRequest request) throws UnitOfMeasureException {

        return getById(id).zipWith(Mono.just(request)).map(tuple -> {
            UnitOfMeasure entity = tuple.getT1();
            UnitOfMeasureRequest updateRequest = tuple.getT2();
            entity.setCode(updateRequest.getCode());
            entity.setWording(updateRequest.getWording());
            return unitOfMesureRepository.save(entity);
        });
    }


    @Override
    public Mono<UnitOfMeasure> getById(Long id) throws UnitOfMeasureException {
        return Mono.just(unitOfMesureRepository.findById(id).orElseThrow(() -> new UnitOfMeasureException("Unit of Measure not found")));
    }

    @Override
    public Mono<Void> deleteUnitOfMeasure(Long id) {
       return null;
    }



}

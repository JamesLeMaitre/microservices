package com.esmc.gestionAvr.putonchain.services.impl;

import com.esmc.gestionAvr.putonchain.entities.StockRoomType;
import com.esmc.gestionAvr.putonchain.repositories.CenterTypeRepository;
import com.esmc.gestionAvr.putonchain.repositories.StockRoomTypeRepository;
import com.esmc.gestionAvr.putonchain.requests.StockRoomTypeRequest;
import com.esmc.gestionAvr.putonchain.services.StockRoomTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class StockRoomTypeImpl implements StockRoomTypeService {
    private final StockRoomTypeRepository stockRoomTypeRepository;
    private final CenterTypeRepository centerTypeRepository;

    @Override
    public Mono<StockRoomType> addStockRoomType(Mono<StockRoomTypeRequest> requestMono) {
        return requestMono.flatMap(request -> Mono.just(stockRoomTypeRepository.save(StockRoomType.builder().wording(request.getWording()).centerType(centerTypeRepository.findById(request.getCenterType().getId()).get()).build())));
    }

    @Override
    public Flux<StockRoomType> getAll() {
        return Flux.fromIterable(stockRoomTypeRepository.findAll());
    }

    @Override
    public Flux<StockRoomType> getAllByCenterType(Long id) {
        return Flux.fromIterable(stockRoomTypeRepository.findByCenterType_Id(id));
    }
}

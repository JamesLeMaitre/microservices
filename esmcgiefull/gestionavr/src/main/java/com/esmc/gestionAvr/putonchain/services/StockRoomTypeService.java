package com.esmc.gestionAvr.putonchain.services;

import com.esmc.gestionAvr.putonchain.entities.StockRoomType;
import com.esmc.gestionAvr.putonchain.requests.StockRoomTypeRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StockRoomTypeService {
    Mono<StockRoomType> addStockRoomType(Mono<StockRoomTypeRequest> requestMono);

    Flux<StockRoomType> getAll();
    Flux<StockRoomType> getAllByCenterType(Long id);
}

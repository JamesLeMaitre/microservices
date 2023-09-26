package com.esmc.gestionAvr.putonchain.services;

import com.esmc.gestionAvr.putonchain.entities.Category;
import com.esmc.gestionAvr.putonchain.entities.ParameterProducts;
import com.esmc.gestionAvr.putonchain.entities.StockRoomType;
import com.esmc.gestionAvr.putonchain.requests.ParameterProductsRequest;
import reactor.core.publisher.Mono;

public interface ParameterProductsService {
    Mono<ParameterProducts> addParameter(Mono<ParameterProductsRequest> requestMono);

    Mono<ParameterProducts> getParameterByCategory(StockRoomType stockRoomType, Category category);
}

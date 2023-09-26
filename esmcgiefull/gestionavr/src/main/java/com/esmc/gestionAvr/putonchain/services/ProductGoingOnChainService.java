package com.esmc.gestionAvr.putonchain.services;

import com.esmc.gestionAvr.putonchain.requests.ProductGoingOnChainRequest;
import reactor.core.publisher.Mono;

public interface ProductGoingOnChainService {
    Mono<String> addProductGoingOnChain(Mono<ProductGoingOnChainRequest> requestMono);

//    Mono<Void> addProductGoingOnChainv2(Mono<ProductGoingOnChainRequest> requestMono);
}

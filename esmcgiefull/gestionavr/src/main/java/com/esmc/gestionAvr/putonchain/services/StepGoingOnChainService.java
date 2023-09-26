package com.esmc.gestionAvr.putonchain.services;

import com.esmc.gestionAvr.putonchain.entities.StepGoingOnChain;
import com.esmc.gestionAvr.putonchain.exceptions.StepGoingOnChainException;
import com.esmc.gestionAvr.putonchain.requests.StepGoingOnChainRequest;
import reactor.core.publisher.Mono;

public interface StepGoingOnChainService {
    Mono<StepGoingOnChain> addStepOnChain(Mono<StepGoingOnChainRequest> requestMono);

    Mono<StepGoingOnChain> getActiveStep(Long id) throws StepGoingOnChainException;

    Mono<StepGoingOnChain> updateActiveState(Long id,StepGoingOnChainRequest request) throws StepGoingOnChainException;


}

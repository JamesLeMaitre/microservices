package com.esmc.gestionAvr.putonchain.services.impl;

import com.esmc.gestionAvr.feign.DetailAgrClient;
import com.esmc.gestionAvr.putonchain.entities.StepGoingOnChain;
import com.esmc.gestionAvr.putonchain.exceptions.StepGoingOnChainException;
import com.esmc.gestionAvr.putonchain.repositories.StepGoingOnChainRepository;
import com.esmc.gestionAvr.putonchain.requests.StepGoingOnChainRequest;
import com.esmc.gestionAvr.putonchain.services.StepGoingOnChainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class StepGoingOnChainImpl implements StepGoingOnChainService {
    private final StepGoingOnChainRepository stepGoingOnChainRepository;
    private final DetailAgrClient detailAgrClient;

    /**
     * @param requestMono
     * @return
     */
    @Override
    public Mono<StepGoingOnChain> addStepOnChain(Mono<StepGoingOnChainRequest> requestMono) {
        return requestMono.flatMap(request -> Mono.just(stepGoingOnChainRepository.save(StepGoingOnChain.builder().state(true).data(request.getData()).step(request.getStep()).detailAgr(request.getDetailAgr()).ksu(detailAgrClient.getDetailById(request.getDetailAgr()).getKsu()).build())));
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Mono<StepGoingOnChain> getActiveStep(Long id) throws StepGoingOnChainException {

        return Mono.just(stepGoingOnChainRepository.findByKsuAndStateIsTrue(detailAgrClient.getDetailById(id).getKsu()).orElseThrow(() -> new StepGoingOnChainException("Step with id " + id + " not found"))).flatMap(step -> {
            if (step.isState()) {
                return Mono.just(step);
            } else {
                return Mono.error(new StepGoingOnChainException("Step with id " + id + " has state false"));
            }
        });
    }


    @Override
    public Mono<StepGoingOnChain> updateActiveState(Long id,StepGoingOnChainRequest request) throws StepGoingOnChainException {
        return getActiveStep(id).flatMap(active -> {
            active.setStep(active.getStep() + 1);
            active.setData(request.getData());
            if (active.getStep() == 4) active.setState(false);
            return Mono.just(stepGoingOnChainRepository.save(active));
        });
    }

}

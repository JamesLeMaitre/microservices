package com.esmc.gestionAvr.putonchain.controllers;

import com.esmc.gestionAvr.putonchain.exceptions.StepGoingOnChainException;
import com.esmc.gestionAvr.putonchain.requests.StepGoingOnChainRequest;
import com.esmc.gestionAvr.putonchain.responses.ErrorResponse;
import com.esmc.gestionAvr.putonchain.responses.HttpResponse;
import com.esmc.gestionAvr.putonchain.services.StepGoingOnChainService;
import com.esmc.gestionAvr.utils.JavaUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.esmc.gestionAvr.putonchain.utils.JavaUtil.errorResponse;
import static com.esmc.gestionAvr.putonchain.utils.JavaUtil.successResponse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = JavaUtils.API_BASE_URL, produces = {APPLICATION_JSON_VALUE})
public class StepGoingOnChainController {

    private final StepGoingOnChainService stepGoingOnChainService;

    @PostMapping(value = "add/stepForChain", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> addStepGoingOnChain(@RequestBody StepGoingOnChainRequest requestMono) {
        return ResponseEntity.status(OK).body(successResponse("Step going on chain added successfully", true, stepGoingOnChainService.addStepOnChain(Mono.just(requestMono))));
    }

    @GetMapping(value = "getActiveStepByDetailAgr/{id}/stepForChain")
    public ResponseEntity<Mono<HttpResponse>> getStepActive(@PathVariable("id")Long id) throws StepGoingOnChainException {

        try {
            return ResponseEntity.status(OK).body(successResponse("Step going on chain  retrieve successfully", true, stepGoingOnChainService.getActiveStep(id)));
        } catch (StepGoingOnChainException step){
            ErrorResponse err = ErrorResponse.builder().build();
            return ResponseEntity.status(BAD_REQUEST).body(errorResponse(step.getMessage(), false, Mono.just(err)));
        }
    }

    @PutMapping(value = "updateStepByDetailAgr/{id}/stepForChain")
    public ResponseEntity<Mono<HttpResponse>> updateByDetAgr(@PathVariable("id")Long id,@RequestBody StepGoingOnChainRequest request) throws StepGoingOnChainException {
        return ResponseEntity.status(OK).body(successResponse("Step going on chain update successfully", true, stepGoingOnChainService.updateActiveState(id,request)));
    }
}

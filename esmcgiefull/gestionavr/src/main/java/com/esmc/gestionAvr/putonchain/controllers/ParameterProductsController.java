package com.esmc.gestionAvr.putonchain.controllers;

import com.esmc.gestionAvr.putonchain.requests.ParameterProductsRequest;
import com.esmc.gestionAvr.putonchain.responses.HttpResponse;
import com.esmc.gestionAvr.putonchain.services.ParameterProductsService;
import com.esmc.gestionAvr.utils.JavaUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.esmc.gestionAvr.putonchain.utils.JavaUtil.successResponse;
import static javax.security.auth.callback.ConfirmationCallback.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = JavaUtils.API_BASE_URL, produces = {APPLICATION_JSON_VALUE})
public class ParameterProductsController {
    private final ParameterProductsService parameterProductsService;

    @PostMapping(value = "add/parameterProductsGoingOnChain", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> addParameterProducts(@RequestBody ParameterProductsRequest requestMono) {
        return ResponseEntity.status(OK).body(successResponse("Parameter for products added successfully", true, parameterProductsService.addParameter(Mono.just(requestMono))));
    }

//    @GetMapping(value = "getParam/parameterProductsGoingOnChain", consumes = {APPLICATION_JSON_VALUE})
//    public ResponseEntity<Mono<HttpResponse>> getParamProducts(@RequestBody ParameterProductsRequest requestMono) {
//        return ResponseEntity.status(OK).body(successResponse("Parameter for products added successfully", true, parameterProductsService.getParameterByCategory(Mono.just(requestMono))));
//    }
}

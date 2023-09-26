package com.esmc.gestionAvr.putonchain.controllers;

import com.esmc.gestionAvr.putonchain.requests.ProductGoingOnChainRequest;
import com.esmc.gestionAvr.putonchain.responses.HttpResponse;
import com.esmc.gestionAvr.putonchain.services.ProductGoingOnChainService;
import com.esmc.gestionAvr.utils.JavaUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.esmc.gestionAvr.putonchain.utils.JavaUtil.successResponse;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = JavaUtils.API_BASE_URL, produces = {APPLICATION_JSON_VALUE})
public class ProductGoingOnChainController {
    private final ProductGoingOnChainService goingOnChainService;

    /**
     * @param requestMono
     * @return
     */
    @PostMapping(value = "add/productsGoingOnChain", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> addProductsOnChain(@RequestBody ProductGoingOnChainRequest requestMono) {
        return ResponseEntity.status(OK).body(successResponse("Product going on chain added successfully", true, goingOnChainService.addProductGoingOnChain(Mono.just(requestMono))));
    }

}

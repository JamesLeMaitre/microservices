package com.esmc.gestionAvr.putonchain.controllers;

import com.esmc.gestionAvr.putonchain.requests.SpaceTypeRequest;
import com.esmc.gestionAvr.putonchain.responses.HttpResponse;
import com.esmc.gestionAvr.putonchain.services.SpaceTypeService;
import com.esmc.gestionAvr.utils.JavaUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.esmc.gestionAvr.putonchain.utils.JavaUtil.successResponse;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = JavaUtils.API_BASE_URL, produces = {APPLICATION_JSON_VALUE})
public class SpaceTypeController {
    private final SpaceTypeService spaceTypeService;

    @PostMapping(value = "add/spaceType", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> addSpace(@RequestBody SpaceTypeRequest requestMono ) {
        return ResponseEntity.status(OK).body(successResponse("Space type added successfully", true, spaceTypeService.addSpaceType(Mono.just(requestMono))));
    }

    @GetMapping(value = "getAll/spaceType")
    public ResponseEntity<Mono<HttpResponse>> getAllSpaceType() {
        return ResponseEntity.status(OK).body(successResponse("Space type  retrieve successfully", true, spaceTypeService.getAllSpace()));
    }
}

package com.esmc.gestionAvr.putonchain.controllers;

import com.esmc.gestionAvr.putonchain.requests.CenterTypeRequest;
import com.esmc.gestionAvr.putonchain.responses.HttpResponse;
import com.esmc.gestionAvr.putonchain.services.CenterTypeService;
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
public class CenterTypeController {
    private final CenterTypeService centerTypeService;

    @PostMapping(value = "add/centerType", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> addCenter(@RequestBody CenterTypeRequest requestMono) {
        return ResponseEntity.status(OK).body(successResponse("Center type added successfully", true, centerTypeService.addCenterType(Mono.just(requestMono))));
    }

    @GetMapping(value = "getAll/centerType")
    public ResponseEntity<Mono<HttpResponse>> getAllCenter() {
        return ResponseEntity.status(OK).body(successResponse("All Center type retreive  successfully", true, centerTypeService.getAllCenter()));
    }
}

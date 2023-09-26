package com.esmc.gestionAvr.putonchain.controllers;

import com.esmc.gestionAvr.putonchain.exceptions.UnitOfMeasureException;
import com.esmc.gestionAvr.putonchain.requests.UnitOfMeasureRequest;
import com.esmc.gestionAvr.putonchain.responses.HttpResponse;
import com.esmc.gestionAvr.putonchain.services.UnitOfMeasureService;
import com.esmc.gestionAvr.utils.JavaUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static com.esmc.gestionAvr.putonchain.utils.JavaUtil.successResponse;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = JavaUtils.API_BASE_URL, produces = {APPLICATION_JSON_VALUE})
public class UnitOfMeasureController {
    private final UnitOfMeasureService unitOfMeasureService;

    @PostMapping(value = "add/unitOfMeasure", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> addUnitOfMeasure(@RequestBody UnitOfMeasureRequest request) {
        return ResponseEntity.status(OK).body(successResponse("Unit Of Measure added successfully", true, unitOfMeasureService.addUnitOfMeasure(Mono.just(request).block(Duration.ofSeconds(5)))));
    }

    @GetMapping(value = "getAll/unitOfMeasure")
    public ResponseEntity<Mono<HttpResponse>> getAllUnitOfMeasures() {
        return ResponseEntity.status(OK).body(successResponse("All Unit Of Measure  retrieve successfully", true, unitOfMeasureService.getAllUnitOfMeasure()));
    }

    @GetMapping(value = "getById/{id}/unitOfMeasure", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> getById(@PathVariable("id")Long id) throws UnitOfMeasureException {
        return ResponseEntity.status(OK).body(successResponse("Unit Of Measure  retrieve successfully", true, unitOfMeasureService.getById(id)));
    }

    @PutMapping(value = "update/{id}/unitOfMeasure", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> updateUnitOfMeasures(@PathVariable("id") Long id, @RequestBody UnitOfMeasureRequest request) throws UnitOfMeasureException {
        return ResponseEntity.status(OK).body(successResponse(" Unit Of Measure update successfully", true, unitOfMeasureService.updateUnitOfMeasure(id, request)));

    }

}

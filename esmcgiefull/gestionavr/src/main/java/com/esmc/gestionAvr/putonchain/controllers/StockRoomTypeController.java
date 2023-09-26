package com.esmc.gestionAvr.putonchain.controllers;

import com.esmc.gestionAvr.putonchain.requests.StockRoomTypeRequest;
import com.esmc.gestionAvr.putonchain.responses.HttpResponse;
import com.esmc.gestionAvr.putonchain.services.StockRoomTypeService;
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
public class StockRoomTypeController {
    private final StockRoomTypeService stockRoomTypeService;

    @PostMapping(value = "add/stockRoom", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> addStockRoom(@RequestBody StockRoomTypeRequest requestMono) {
        return ResponseEntity.status(OK).body(successResponse("Stock room added successfully", true, stockRoomTypeService.addStockRoomType(Mono.just(requestMono))));
    }

    @GetMapping(value = "getAll/stockRoom")
    public ResponseEntity<Mono<HttpResponse>> getAllStockRoom() {
        return ResponseEntity.status(OK).body(successResponse("Stock room  retrieve successfully", true, stockRoomTypeService.getAll()));
    }

    @GetMapping(value = "getAllByCenterType/{id}/stockRoom")
    public ResponseEntity<Mono<HttpResponse>> getAllByCenterType(@PathVariable("id")Long id) {
        return ResponseEntity.status(OK).body(successResponse("Stock room  retrieve successfully", true, stockRoomTypeService.getAllByCenterType(id)));
    }
}

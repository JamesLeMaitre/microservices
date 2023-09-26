package com.esmc.gestionAvr.putonchain.controllers;

import com.esmc.gestionAvr.putonchain.exceptions.CategoryException;
import com.esmc.gestionAvr.putonchain.exceptions.ProductsException;
import com.esmc.gestionAvr.putonchain.requests.ProductRequest;
import com.esmc.gestionAvr.putonchain.responses.ErrorResponse;
import com.esmc.gestionAvr.putonchain.responses.HttpResponse;
import com.esmc.gestionAvr.putonchain.services.ProductService;
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
public class ProductController {
    private ProductService productService;

    /**
     * @param request
     * @return
     */
    @PostMapping(value = "add/products", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> addProducts(@RequestBody ProductRequest request) {
        try {
            return ResponseEntity.status(OK).body(successResponse("Product added successfully", true, productService.addProduct(Mono.just(request).block())));
        } catch (CategoryException ex) {
            ErrorResponse err = ErrorResponse.builder().status(false).raison(ex.getMessage()).build();
            return ResponseEntity.status(BAD_REQUEST).body(errorResponse("", false, Mono.just(err)));
        }
    }


    @GetMapping(value = "getAll/products")
    public ResponseEntity<Mono<HttpResponse>> getAllProducts() {
        return ResponseEntity.status(OK).body(successResponse("All Product retrieve successfully", true, productService.getAllProducts()));
    }

    /**
     * @param id
     * @return
     * @throws ProductsException
     */

    @GetMapping(value = "getById/{id}/products", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> getAProductById(@PathVariable("id") Long id) throws ProductsException {
        return ResponseEntity.status(OK).body(successResponse("One product retrieve successfully", true, productService.getById(id)));
    }



    /**
     * @param id
     * @param request
     * @return
     */
    @PutMapping(value = "update/{id}/product", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> updateProducts(@PathVariable("id") Long id, @RequestBody ProductRequest request) throws ProductsException {
        return ResponseEntity.status(OK).body(successResponse(" Product update successfully", true, productService.updateProducts(id, request)));
    }

    @GetMapping(value = "getById/{id}/productByDetailAgr", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> getAProductByDetailAgr(@PathVariable("id") Long id)  {
        return ResponseEntity.status(OK).body(successResponse("Products retrieve successfully", true, productService.getByDetailAgr(id)));
    }

}

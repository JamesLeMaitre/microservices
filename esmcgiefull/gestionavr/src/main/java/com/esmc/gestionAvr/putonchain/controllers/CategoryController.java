package com.esmc.gestionAvr.putonchain.controllers;

import com.esmc.gestionAvr.putonchain.exceptions.CategoryException;
import com.esmc.gestionAvr.putonchain.exceptions.UnitOfMeasureException;
import com.esmc.gestionAvr.putonchain.requests.CategoryRequest;
import com.esmc.gestionAvr.putonchain.responses.HttpResponse;
import com.esmc.gestionAvr.putonchain.services.CategoryService;
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
public class CategoryController {
    private CategoryService categoryService;

    /**
     * @param request
     * @return
     */
    @PostMapping(value = "add/category", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> addCategory(@RequestBody CategoryRequest request) throws UnitOfMeasureException {
        return ResponseEntity.status(OK)
                .body(successResponse("Category add successfully",true,categoryService.addCategory(Mono.just(request).block())));
    }

    /**
     * @return
     */
    @GetMapping(value = "getAll/category")
    public ResponseEntity<Mono<HttpResponse>> getAllCategory() {
        return ResponseEntity.status(OK)
                .body(successResponse("All Categories retrieve successfully",true,categoryService.getAllCategory()));
    }

    /**
     * @param id
     * @return
     * @throws CategoryException
     */

    @GetMapping(value = "getById/category/{id}", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> getAllCategoryById(@PathVariable Long id) throws CategoryException {
        return ResponseEntity.status(OK)
                .body(successResponse("One Category retrieve successfully",true,categoryService.getById(id)));
    }

    @PutMapping(value = "update/{id}/category", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Mono<HttpResponse>> updateCategory(@PathVariable("id") Long id,CategoryRequest categoryRequestMono) throws CategoryException {
        return ResponseEntity.status(OK)
                .body(successResponse("Category update successfully",true,categoryService.updateCategory(id,categoryRequestMono)));
    }

}

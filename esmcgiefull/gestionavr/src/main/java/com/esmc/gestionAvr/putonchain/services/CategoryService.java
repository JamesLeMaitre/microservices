package com.esmc.gestionAvr.putonchain.services;

import com.esmc.gestionAvr.putonchain.entities.Category;
import com.esmc.gestionAvr.putonchain.exceptions.CategoryException;
import com.esmc.gestionAvr.putonchain.exceptions.UnitOfMeasureException;
import com.esmc.gestionAvr.putonchain.requests.CategoryRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {
    Mono<Category> addCategory(CategoryRequest request) throws UnitOfMeasureException;

    Flux<Category> getAllCategory();

    Mono<Category> getById(Long id) throws CategoryException;

    Mono<Category> updateCategory(Long id,CategoryRequest categoryRequest) throws CategoryException;

}

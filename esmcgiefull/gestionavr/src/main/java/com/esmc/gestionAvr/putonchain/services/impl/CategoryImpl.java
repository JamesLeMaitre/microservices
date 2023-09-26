package com.esmc.gestionAvr.putonchain.services.impl;

import com.esmc.gestionAvr.putonchain.entities.Category;
import com.esmc.gestionAvr.putonchain.entities.UnitOfMeasure;
import com.esmc.gestionAvr.putonchain.exceptions.CategoryException;
import com.esmc.gestionAvr.putonchain.exceptions.UnitOfMeasureException;
import com.esmc.gestionAvr.putonchain.repositories.CategoryRepository;
import com.esmc.gestionAvr.putonchain.repositories.UnitOfMeasureRepository;
import com.esmc.gestionAvr.putonchain.requests.CategoryRequest;
import com.esmc.gestionAvr.putonchain.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CategoryImpl implements CategoryService {
    private CategoryRepository categoryRepository;
private UnitOfMeasureRepository unitOfMeasureRepository;
    /**
     * @param request
     * @return
     */
    @Override
    public Mono<Category> addCategory(CategoryRequest request) throws UnitOfMeasureException {
        UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.findById(request.getUnitOfMeasure().getId()).orElseThrow(()->new UnitOfMeasureException("Unit Of Measure not found"));
        Category category = Category.builder()
                .wording(request.getWording())
                .unitOfMeasure(unitOfMeasure)
                .code(request.getCode()).build();
        return Mono.just(categoryRepository.save(category));
    }

    /**
     * @return
     */
    public Flux<Category> getAllCategory() {
        return Flux.fromIterable(categoryRepository.findAll());
    }

    /**
     * @param id
     * @return
     * @throws CategoryException
     */
    @Override
    public Mono<Category> getById(Long id) throws CategoryException {
        return Mono.just(categoryRepository.findById(id).orElseThrow(() -> new CategoryException("Category with id :" + id + " not found")));
    }

    @Override
    public Mono<Category> updateCategory(Long id, CategoryRequest categoryRequest) throws CategoryException {
        return getById(id).map(category -> {
           category.setWording(categoryRequest.getWording());
           category.setCode(categoryRequest.getCode());
           category.setUnitOfMeasure(categoryRequest.getUnitOfMeasure());
           return categoryRepository.save(category);
        });

    }

}

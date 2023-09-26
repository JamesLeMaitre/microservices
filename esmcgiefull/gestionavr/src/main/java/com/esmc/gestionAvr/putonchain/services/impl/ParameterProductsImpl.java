package com.esmc.gestionAvr.putonchain.services.impl;

import com.esmc.gestionAvr.putonchain.entities.Category;
import com.esmc.gestionAvr.putonchain.entities.ParameterProducts;
import com.esmc.gestionAvr.putonchain.entities.StockRoomType;
import com.esmc.gestionAvr.putonchain.repositories.CategoryRepository;
import com.esmc.gestionAvr.putonchain.repositories.ParameterProductsRepository;
import com.esmc.gestionAvr.putonchain.repositories.StockRoomTypeRepository;
import com.esmc.gestionAvr.putonchain.requests.ParameterProductsRequest;
import com.esmc.gestionAvr.putonchain.services.ParameterProductsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ParameterProductsImpl implements ParameterProductsService {
    private final ParameterProductsRepository repository;
    private final StockRoomTypeRepository stockRoomTypeRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Mono<ParameterProducts> addParameter(Mono<ParameterProductsRequest> requestMono) {
        return requestMono.flatMap(request -> Mono.just(repository.save(
                ParameterProducts.builder()
                        .wording(request.getWording())
                        .maximumQuantity(request.getMaximumQuantity())
                        .minimumQuantity(request.getMinimumQuantity())
                        .category(categoryRepository.findById(request.getCategory().getId()).get())
                        .stockRoomType(stockRoomTypeRepository.findById(request.getStockRoomType().getId()).get())
                        .build())));
    }

    @Override
    public Mono<ParameterProducts> getParameterByCategory(StockRoomType stockRoomType, Category category) {
        Optional<ParameterProducts> optionalProduct = repository.findByStockRoomTypeAndCategory(stockRoomType, category);
        return optionalProduct.map(Mono::just).orElseGet(Mono::empty);
    }


}

package com.esmc.gestionAvr.putonchain.services.impl;

import com.esmc.gestionAvr.feign.DetailAgrClient;
import com.esmc.gestionAvr.putonchain.entities.Category;
import com.esmc.gestionAvr.putonchain.entities.Products;
import com.esmc.gestionAvr.putonchain.exceptions.CategoryException;
import com.esmc.gestionAvr.putonchain.exceptions.ProductsException;
import com.esmc.gestionAvr.putonchain.repositories.CategoryRepository;
import com.esmc.gestionAvr.putonchain.repositories.ProductRepository;
import com.esmc.gestionAvr.putonchain.requests.ProductRequest;
import com.esmc.gestionAvr.putonchain.services.CategoryService;
import com.esmc.gestionAvr.putonchain.services.ProductService;
import com.esmc.gestionAvr.putonchain.utils.JavaConvert;
import com.esmc.gestionAvr.putonchain.utils.JavaUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class ProductImpl implements ProductService {
    private JavaConvert convert;
    private final JavaUtil javaUtil;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;
    private final DetailAgrClient detailAgrClient;

    /**
     * @param req
     * @return
     */
    @Override
    public Mono<Products> addProduct(ProductRequest req) throws CategoryException {
        Category category = categoryRepository.findById(req.getCategory().getId()).orElseThrow(() -> new CategoryException("Category with id : " + req.getCategory().getId() + " not found"));
        Products product = Products.builder()
                .wording(req.getWording())
                .quantity(req.getQuantity())
                .unitPrice(req.getUnitPrice())
                .state(true)
                .description(req.getDescription())
                .ksu(detailAgrClient.getDetailById(req.getIdDetailAgr()).getKsu()).idDetailAgr(req.getIdDetailAgr())
                .unitTotal(JavaUtil.multiplication(req.getQuantity(), req.getUnitPrice()))
                .category(category).build();
        return Mono.just(productRepository.save(product));
    }

    /**
     * @return
     */
    @Override
    public Flux<Products> getAllProducts() {
        return Flux.fromIterable(productRepository.findAll());
    }

    /**
     * @param id
     * @return
     * @throws ProductsException
     */
    @Override
    public Mono<Products> getById(Long id) throws ProductsException {
        return Mono.just(productRepository.findById(id).orElseThrow(() -> new ProductsException("Products with id " + id + " not found"))).flatMap(product -> {
            if (product.isState()) {
                return Mono.just(product);
            } else {
                return Mono.error(new ProductsException("Products with id " + id + " has state false"));
            }
        });
    }


    /**
     * @param id
     * @param p
     * @return
     * @throws ProductsException
     */

    @Override
    public Mono<Products> updateProducts(Long id, ProductRequest p) throws ProductsException {
        return getById(id).flatMap(product -> {
            try {
                return categoryService.getById(p.getCategory().getId()).map(category -> {
                    product.setWording(p.getWording());
                    product.setQuantity(p.getQuantity());
                    product.setUnitPrice(p.getUnitPrice());
                    product.setDescription(p.getDescription());
                    product.setUnitTotal(p.getUnitTotal());
//                    product.setCategory(category);
                    product.setIdDetailAgr(p.getIdDetailAgr());
                    return productRepository.save(product);
                });
            } catch (CategoryException e) {
                return Mono.error(new RuntimeException(e));
            }
        });
    }


    /**
     * @param id
     * @return
     */
    @Override
    public Flux<Products> getByDetailAgr(Long id) {
        return Flux.fromIterable(productRepository.findByIdDetailAgrAndStateIsTrue(id)).cache();
    }


}


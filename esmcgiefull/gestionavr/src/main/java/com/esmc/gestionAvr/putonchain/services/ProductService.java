package com.esmc.gestionAvr.putonchain.services;

import com.esmc.gestionAvr.putonchain.entities.Products;
import com.esmc.gestionAvr.putonchain.exceptions.CategoryException;
import com.esmc.gestionAvr.putonchain.exceptions.ProductsException;
import com.esmc.gestionAvr.putonchain.requests.ProductRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<Products> addProduct(ProductRequest req) throws CategoryException;
    Flux<Products> getAllProducts();
    Mono<Products> getById(Long id) throws ProductsException;

     Mono<Products> updateProducts(Long id , ProductRequest p) throws ProductsException;

     Flux<Products> getByDetailAgr(Long id);
}

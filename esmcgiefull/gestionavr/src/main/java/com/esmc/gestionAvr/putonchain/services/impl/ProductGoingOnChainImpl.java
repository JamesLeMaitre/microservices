package com.esmc.gestionAvr.putonchain.services.impl;

import com.esmc.gestionAvr.putonchain.entities.ProductGoingOnChain;
import com.esmc.gestionAvr.putonchain.exceptions.ProductGoingOnChainException;
import com.esmc.gestionAvr.putonchain.repositories.CategoryRepository;
import com.esmc.gestionAvr.putonchain.repositories.ProductGoingOnChainRepository;
import com.esmc.gestionAvr.putonchain.repositories.ProductRepository;
import com.esmc.gestionAvr.putonchain.requests.ProductGoingOnChainRequest;
import com.esmc.gestionAvr.putonchain.services.ParameterProductsService;
import com.esmc.gestionAvr.putonchain.services.ProductGoingOnChainService;
import com.esmc.gestionAvr.putonchain.utils.JavaUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class ProductGoingOnChainImpl implements ProductGoingOnChainService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ParameterProductsService parameterProductsService;
    private final ProductGoingOnChainRepository productGoingOnChainRepository;


    @Override
    public Mono<String> addProductGoingOnChain(Mono<ProductGoingOnChainRequest> requestMono) {
        return requestMono.flatMap(request -> {
            productRepository.findByIdAndStateIsTrue(request.getProducts().getId()).ifPresent(products -> {
                if (products.getQuantity() >= request.getQuantity()) {
                    categoryRepository.findById(products.getCategory().getId()).ifPresent(category -> {
                        parameterProductsService.getParameterByCategory(request.getStockRoomType(), category).flatMap(param -> {
                            if (param.getMinimumQuantity() > request.getQuantity()) {
                                log.info("Don't have sufficient quantity : {}", request.getQuantity());
                                return Mono.error(new ProductGoingOnChainException("Product with id: " + products.getId() + " has quantity less than requested quantity"));
                            } else {
                                ProductGoingOnChain goingOnChain = ProductGoingOnChain.builder().parameterProducts(param).stockRoomType(request.getStockRoomType()).spaceType(request.getSpaceType()).quantity(request.getQuantity()).idDetailAgr(request.getIdDetailAgr()).products(products).build();
                                return Mono.just(productGoingOnChainRepository.save(goingOnChain)).flatMap(savedPgc -> {

                                    products.setQuantity(products.getQuantity() - savedPgc.getQuantity());
                                    products.setUnitTotal(JavaUtil.multiplication(products.getQuantity(), products.getUnitPrice()));
                                    if (products.getQuantity() == 0) {
                                        products.setState(false);
                                    }
                                    return Mono.just(productRepository.save(products)).thenReturn(savedPgc);

                                });
                            }
                        }).subscribe();
                    });
                }
            });
            return Mono.justOrEmpty("Value add success");
        });
    }

//    @Override
//    public Mono<Void> addProductGoingOnChainv2(Mono<ProductGoingOnChainRequest> requestMono) {
//        return requestMono.flatMap(request -> {
//            productRepository.findByIdAndStateIsTrue(request.getProducts().getId()).ifPresent(products -> {
//                final Object s = this.verifyQuantity(products.getQuantity(), request.getQuantity()) ?
//                        Mono.error(new ProductGoingOnChainException("Product with id: " + products.getId() + " has quantity less than requested quantity"))
//                        : ""
//            });
//            return null;
//        });}

//
//                if (products.getQuantity() >= request.getQuantity()) {
//                    categoryRepository.findById(products.getCategory().getId()).ifPresent(category -> {
//                        parameterProductsService.getParameterByCategory(request.getStockRoomType(), category).flatMap(param -> {
//                            if (param.getMinimumQuantity() > request.getQuantity()) {
//                                log.info("Don't have sufficient quantity : {}", request.getQuantity());
//                                return Mono.error(new ProductGoingOnChainException("Product with id: " + products.getId() + " has quantity less than requested quantity"));
//                            } else {
//                                ProductGoingOnChain goingOnChain = ProductGoingOnChain.builder().parameterProducts(param).stockRoomType(request.getStockRoomType()).spaceType(request.getSpaceType()).quantity(request.getQuantity()).idDetailAgr(request.getIdDetailAgr()).products(products).build();
//                                return Mono.just(productGoingOnChainRepository.save(goingOnChain)).flatMap(savedPgc -> {
//
//                                    products.setQuantity(products.getQuantity() - savedPgc.getQuantity());
//                                    products.setUnitTotal(JavaUtil.multiplication(products.getQuantity(), products.getUnitPrice()));
//                                    if (products.getQuantity() == 0) {
//                                        products.setState(false);
//                                    }
//                                    return Mono.just(productRepository.save(products)).thenReturn(savedPgc);
//
//                                });
//                            }
//                        }).subscribe();
//                    });
//                }
//            });
//            return Mono.justOrEmpty("Value add success");
//        });
//    }

    public boolean verifyQuantity(int x, int y){
        return x >= y;
    }


}
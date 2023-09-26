package com.esmc.gestionAvr.putonchain.utils;

import com.esmc.gestionAvr.putonchain.responses.HttpResponse;
import com.esmc.gestionAvr.putonchain.responses.SuccessResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class JavaUtil {
    public static <T> Mono<HttpResponse> successResponse(String message, Boolean status, Mono<T> data) {
        return data.map(t -> SuccessResponse.builder().data(t).build())
                .doOnNext(successResponse -> successResponse.setMessage(message))
                .doOnNext(successResponse -> successResponse.setStatus(status))
                .cast(HttpResponse.class);
    }

    public static <T> Mono<HttpResponse> successResponse(String message, Boolean status, Flux<T> data) {
        return data.collectList().map(ts -> SuccessResponse.builder().data(ts).build())
                .doOnNext(successResponse -> successResponse.setMessage(message))
                .doOnNext(successResponse -> successResponse.setStatus(status))
                .cast(HttpResponse.class);
    }

    public static <T> Mono<HttpResponse> errorResponse(String message, Boolean status, Mono<T> data) {
        return data.map(t -> SuccessResponse.builder().data(t).build())
                .doOnNext(successResponse -> successResponse.setMessage(message))
                .doOnNext(successResponse -> successResponse.setStatus(status))
                .cast(HttpResponse.class);
    }

    public static <T> Mono<HttpResponse> errorResponse(String message, Boolean status, Flux<T> data) {
        return data.collectList().map(ts -> SuccessResponse.builder().data(ts).build())
                .doOnNext(successResponse -> successResponse.setMessage(message))
                .doOnNext(successResponse -> successResponse.setStatus(status))
                .cast(HttpResponse.class);
    }

    public static double multiplication(int quantity,double unitPrice){
        return quantity * unitPrice;
    }
}

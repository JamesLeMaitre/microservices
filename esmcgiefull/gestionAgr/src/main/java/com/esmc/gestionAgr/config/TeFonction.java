package com.esmc.gestionAgr.config;


import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/*@FeignClient(value = "man", url = "192.168.17.197:8080/api/terminalEchange/")
public interface TeFonction {

    @GetMapping("save")
    public ResponseEntity<?> addNewSupportMarchandEnchage(List<DetailAgr> list);


}*/
/*

@Component
class TEClientFallbackFactory implements FallbackFactory<TeFonction> {



    @Override
    public TeFonction create(Throwable cause) {
        int status=((FeignException) cause).status();

        return new TeFonction() {
            @Override
            public ResponseEntity<?> addNewSupportMarchandEnchage(List<DetailAgr> list) {
                return  new ResponseEntity<>(((FeignException) cause).getMessage(), HttpStatus.valueOf(status));
            }
        };
    }
}*/

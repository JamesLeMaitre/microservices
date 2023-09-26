package com.esmc.gestionAgr.config;/*
package com.esmc.gestionAgr.config;

import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "ma", url = "192.168.17.205:8081/ksus/")
public interface ManesJSON {


    @GetMapping("getById/{id}")
    ResponseEntity<?> getById(@PathVariable("id") Long id);

}
@Component
class KsuClientFallbackFactory implements FallbackFactory<ManesJSON>{

    @Override
    public ManesJSON create(Throwable cause) {

        int status=((FeignException) cause).status();
        return new ManesJSON() {
            @Override
            public ResponseEntity<?> getById(Long id) {
                return  new ResponseEntity<>(((FeignException) cause).getMessage(), HttpStatus.valueOf(status));
            }
        };
    }

}
*/

package com.esmc.gestionAvr.tokens.controllers;


import com.esmc.gestionAvr.tokens.model.TokenEntity;
import com.esmc.gestionAvr.tokens.models.TokenEntitie;
import com.esmc.gestionAvr.tokens.service.TokenServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/amount/")
public class AmountTokenController {

    private final TokenServiceImpl tokenService;

    public AmountTokenController(TokenServiceImpl tokenService) {
        this.tokenService = tokenService;
    }


    @GetMapping("encryptToken")
    public String encryptToken(@RequestBody TokenEntity tokenEntitie) throws Exception {

       return tokenService.generateCode2(tokenEntitie);

    }

    @GetMapping("setFieldsFromDecryptedString/{code}")
    public TokenEntity setFieldsFromDecryptedString(@PathVariable("code") String code) throws Exception {

        return tokenService.setFieldsFromDecryptedString(code);

    }

    @GetMapping("encrypt/{plaintext}/{key}")
    public String encrypt(@PathVariable("plaintext") String plaintext, @PathVariable("key") int key) throws Exception {

        return tokenService.encrypt(plaintext,key);

    }
    @GetMapping("decrypt/{plaintext}/{key}")
    public String encryptToken(@PathVariable("plaintext") String plaintext, @PathVariable("key") int key) throws Exception {

        return tokenService.decrypt(plaintext,key);

    }


}

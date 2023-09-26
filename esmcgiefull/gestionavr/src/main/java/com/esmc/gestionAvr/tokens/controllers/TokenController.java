package com.esmc.gestionAvr.tokens.controllers;

import com.esmc.gestionAvr.entities.DataSupport;
import com.esmc.gestionAvr.tokens.entities.Token;
import com.esmc.gestionAvr.tokens.entities.TokenRequest;
import com.esmc.gestionAvr.tokens.services.TokenInterface;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import utiles.DataFormatter;

import javax.ws.rs.Path;
import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@AllArgsConstructor
@RequestMapping("api/tokens/")
public class TokenController  extends DataFormatter<Token> {

    private final TokenInterface service;

    @PostMapping("save")
    public Object setToken(@RequestBody TokenRequest tokenRequest) {
        try {
            Token token = service.addToken(tokenRequest.getLibelle(), tokenRequest.getIdksu(), tokenRequest.getValue());
            if(token == null){
                return  renderStringData(false, "","Data not found");
            }
            return  renderData(true, token,"Operation Successfully !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }

    @PostMapping("cumul-de-token")
    public Object setAllToken(@RequestBody TokenRequest tokenRequest) {
        try {
            Token token = service.cumulToken(tokenRequest.getTokens());
            if(token == null){
                return  renderStringData(false, "","Data not found");
            }
            return  renderData(true, token,"Operation Successfully !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }

    @PostMapping("getById/idToken/{idToken}")
    public Object getBY(@PathVariable("idToken") Long idToken) {
        try {
            Token token = service.getById(idToken);
            if(token == null){
                return  renderStringData(false, "","Data not found");
            }
            return  renderData(true, token,"Operation Successfully !");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  "Error while processing "+e;
        }
    }
}

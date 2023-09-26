package com.esmcgie;

import com.esmcgie.models.RequestOptions;
import com.esmcgie.models.ResponseTransaction;
import com.esmcgie.requests.TransactionInfo;
import com.esmcgie.services.CorisService;
import com.esmcgie.services.impls.CorisServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        try {
            //codePays=2
            //26&telephone=76477115&codePv=PV025&codeRetrait=02154&montant=1000
            RequestOptions options = RequestOptions.builder().clientId("ESMCGIE")
                    .clientSecret("$2a$10$vIIt8T2W5XE3k8PFUXT3dO5XQY2sr5eME3XWErS73WajkJxpz75x2").build();
            CorisService corisService = new CorisServiceImpl(options);
           // Object object = corisService.hashRequestParametersByWonder("22890159483$2a$10$vIIt8T2W5XE3k8PFUXT3dO5XQY2sr5eME3XWErS73WajkJxpz75x2");
            //log.info("Hash by merveil {}", object);
          Object object = corisService.getClientInformation("228", "90159483");
             log.info("client information by wonder", object);
        // corisService.sendOTPCode("228", "90159483");
        // Object object = corisService.transaction("228", "90159483", "0074267744", "65247", "100");
            // log.info("service paiement INTERNET", object)
            //Object object = corisService.payment("228", "90159483", "0074267744", "100", "85683");
         //   log.info("service paiement de bien", object);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
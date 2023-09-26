package com.esmc.gestionAvr.crons;

import com.esmc.gestionAvr.controllers.AutomateControllerV2;
import com.esmc.gestionAvr.security.AuthenticationUtil;
import com.esmc.gestionAvr.services.AutomateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ScheduledJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledJob.class);



    //@Scheduled(fixedRate = 10000)
    public void run() {

        AuthenticationUtil.configureAuthentication("ROLE_ADMIN");

        String message = "DFD DFD";
        System.out.println(message);
        //automateControllerV2.operateTransactionv2();
        LOGGER.debug("Received message: {}", message);

       // AuthenticationUtil.clearAuthentication();
    }
}
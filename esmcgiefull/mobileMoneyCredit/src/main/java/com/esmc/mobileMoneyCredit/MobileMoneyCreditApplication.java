package com.esmc.mobileMoneyCredit;

import com.esmc.mobileMoneyCredit.Service.AttachmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class MobileMoneyCreditApplication {


    public static void main(String[] args) {
        SpringApplication.run(MobileMoneyCreditApplication.class, args);
    }

}

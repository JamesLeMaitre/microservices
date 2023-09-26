package com.esmc.gestionPayement;

import com.esmc.gestionPayement.ServicesInterface.TransactionAdminInterface;
import com.esmc.gestionPayement.ServicesInterface.TransactionInRequestServiceInterface;
import com.esmc.gestionPayement.security.RunAs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableFeignClients
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableScheduling
public class GestionPayementApplication {

	@Autowired
	TransactionInRequestServiceInterface transactionInRequestServiceInterface;


	public static void main(String[] args) {
		SpringApplication.run(GestionPayementApplication.class, args);
	}

//	@Scheduled(fixedRate = 10000)
//	public void scheduleTransaction(){
//		RunAs.runAsAdmin(() -> {
//			System.out.println("Execute Taxk Fixed rate task - " + System.currentTimeMillis() / 100);
//			transactionInRequestServiceInterface.checkTransaction();
//		});
//	}


	//@Scheduled(cron = "0 0 23 * * ?")
	public void scheduleTransactionAdmin(){
		RunAs.runAsAdmin(() -> {
			System.out.println("Execute Taxk Fixed rate task - " + System.currentTimeMillis() / 100);
			transactionInRequestServiceInterface.checkTransactionAdmin();
		});
	}
}

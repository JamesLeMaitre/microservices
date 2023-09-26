package com.esmc.gestionAvr;

import com.esmc.gestionAvr.controllers.AutomateControllerV2;
import com.esmc.gestionAvr.controllers.PanierFifoController;
import com.esmc.gestionAvr.repositories.TourRepository;
import com.esmc.gestionAvr.services.AchatProduitPOp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
@EnableCaching
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class GestionAvrApplication {

	final
	AutomateControllerV2 automateControllerV2;

	final
	PanierFifoController panierFifoController;
	 final TourRepository tourRepository;
	 final AchatProduitPOp achatProduitPOpService;

	public GestionAvrApplication(AutomateControllerV2 automateControllerV2,
								 PanierFifoController panierFifoController, TourRepository tourRepository, AchatProduitPOp achatProduitPOpService) {
		this.automateControllerV2 = automateControllerV2;
		this.panierFifoController = panierFifoController;
		this.tourRepository = tourRepository;
		this.achatProduitPOpService = achatProduitPOpService;
	}

//	private  TourToBasketServiceImpl tourToBasketService;

	public static void main(String[] args) {
		SpringApplication.run(GestionAvrApplication.class, args);
		//new File(USER_FOLDER).mkdirs();
	}


}

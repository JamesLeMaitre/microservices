package com.esmc.gestionAgr;

import com.esmc.gestionAgr.fifo.entities.Tour;
import com.esmc.gestionAgr.fifo.repositories.TourRepository;
import com.esmc.gestionAgr.fifo.services.TourToBasketService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class GestionAgrApplication {
	private final TourRepository tourRepository;
	private final TourToBasketService tourToBasketService;

	public GestionAgrApplication(TourRepository tourRepository, TourToBasketService tourToBasketService) {
		this.tourRepository = tourRepository;
		this.tourToBasketService = tourToBasketService;
	}

	public static void main(String[] args) {
		SpringApplication.run(GestionAgrApplication.class, args);
	}

	@Scheduled(fixedRate = 1000)
	public void scheduleFixedRateTask() throws Exception {
		if(tourRepository.findAll().size()<2){
			tourRepository.save(new Tour("Tour"));
			tourRepository.save(new Tour("Basket"));
		}
		tourToBasketService.preProcessing();
		tourToBasketService.setBasketDate();

	}
}





package com.esmc.gestionAvr;

import com.esmc.gestionAvr.services.AchatProduitPOp;
import com.esmc.gestionAvr.tokens.repositories.ReferenceTokenRepository;
import com.esmc.gestionAvr.tokens.services.ReferencTokenInterface;
import com.esmc.gestionAvr.tokens.services.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class GestionAvrApplicationTests {

	@Autowired
	private TokenService service;

	@Autowired
	private ReferencTokenInterface tokenInterface;
	@Autowired
	 AchatProduitPOp achatProduitPOpService;
	@Autowired
	private ReferenceTokenRepository referenceTokenRepository;

	@Autowired
	private AchatProduitPOp  achatProduitPOp;



	@Test
	void contextLoads() throws Exception {
//		Token encrypt = service.addToken("James",7L,7892645.05);
//
//		log.info("token : {}",encrypt);
		tokenInterface.tokenGIE();
//		ReferencToken GIE=   referenceTokenRepository.getByIdDetailAgrGIE();
//		System.out.println("==============================================");
//		System.out.println(GIE);
//		System.out.println("==============================================");
//		log.info("GIE TOken : {}",GIE);
//		AchatProduitInput data = AchatProduitInput.builder()
//				.description("ACHAT DE CERTIFICATION")
//				.idCategoryAvr(1L)
//				.idTypeAvr(2L)
//				.libelle("CERTIFICATION")
//				.prixUnitaire(70000.0)
//				.idVague(1L)
//				.quantite(1)
//				.build();
//		achatProduitPOpService.achatProduitPreOpEnLigne(data);
//		AchatProduitInput data = AchatProduitInput.builder()
//				.description("FIL")
//				.idCategoryAvr(1L)
//				.idTypeAvr(2L)
//				.libelle("FIL")
//				.idVague(1L)
//				.idDetailAgrAcheteur(3L)
//				.prixUnitaire(10000.0)
//				.quantite(1)
//				.build();
//		achatProduitPOp.achatProduitPreOpEnLigne(data);


//			String date = "2023-02-15 19:56:17.0";
//			LocalDateTime currentDate = LocalDateTime.now();
//			LocalDateTime processingDate = LocalDateTime.parse(date.substring(0,19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//
//			Duration duration = Duration.between(processingDate, currentDate);
//
//			log.info("Hours : {}", duration.toHours());

	}

}

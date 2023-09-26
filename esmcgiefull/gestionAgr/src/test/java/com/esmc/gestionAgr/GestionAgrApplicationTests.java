package com.esmc.gestionAgr;

import com.esmc.gestionAgr.fifo.services.DetailSMEchangeService;
import com.esmc.gestionAgr.fifo.services.TourToBasketService;
import com.esmc.gestionAgr.fifo.utils.AccountConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GestionAgrApplicationTests {

	@Autowired
	private TourToBasketService tourToBasketService;

	@Autowired
	private  DetailSMEchangeService detailSMEchangeService;

	@Test
	void contextLoads() throws Exception {
//		tourToBasketService.preProcessing();
		detailSMEchangeService.mutationPourAchatFifo(AccountConstant.TeEsmcGieFranchiseZeroOBPS, 3L, 61000.0);
	}

}

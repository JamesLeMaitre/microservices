package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.Fifo;
import com.esmc.gestionAvr.repositories.FifoRepository;
import com.esmc.gestionAvr.servicesInterfaces.VagueServiceInterface;
import com.esmc.gestionAvr.tokens.entities.ReferencToken;
import com.esmc.gestionAvr.tokens.services.ReferenceTokenImpl;
import com.esmc.gestionAvr.utils.enums.KsuType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ManageTourService {
    private final FifoRepository fifoRepository;
    private VagueServiceInterface vagueService;
    private BasketService basketService;
    private final TourService tourService;
    private final SettingsService settingsService;
    private final ReferenceTokenImpl referenceTokenService;
    public void manageTourSeller(Fifo fifo) throws Exception {
        double MAX_TOUR_SELLER_BCI = Double.parseDouble(settingsService.getByCode("PARAM_MAX_TOUR_SELLER_BCI").getValue());
        double MAX_TOUR_BUYER_BAN = Double.parseDouble(settingsService.getByCode("PARAM_MAX_TOUR_BUYER_BAN").getValue());
        if (fifo.getActionAmountSecondType() == 0) {
            throw new Exception("Amount Null");
        } else {
            if (fifo.getKsuType() == KsuType.SELLER) {
                // We work with BCI
                double secondMaxValueBan = fifo.getActionAmountFirstType();

                double difference = MAX_TOUR_SELLER_BCI - fifo.getAmount();


                if (MAX_TOUR_SELLER_BCI >= fifo.getAmount()) {
                    fifo.setDefaultAmountSecondType(fifo.getDefaultAmountSecondType());
                    fifo.setDefaultAmountFirstType(secondMaxValueBan);
                    fifo.setActionAmountFirstType(secondMaxValueBan);
                    fifo.setActionAmountSecondType(fifo.getDefaultAmountSecondType());
                    fifoRepository.save(fifo);
                    tourService.addTour(fifo);
                } else {

                   ReferencToken referencTokenTour=  referenceTokenService.divisionToken(fifo.getReferencToken().getId(),fifo.getKsu().getId(),fifo.getDetailAgr(),
                           -1*difference,MAX_TOUR_SELLER_BCI)[0];
                    ReferencToken referencTokenBasket=  referenceTokenService.divisionToken(fifo.getReferencToken().getId(),fifo.getKsu().getId(),fifo.getDetailAgr(),
                            -1*difference,MAX_TOUR_SELLER_BCI)[1];


                    fifo.setDefaultAmountSecondType(MAX_TOUR_SELLER_BCI);
                    fifo.setDefaultAmountFirstType(vagueService.convertBciBanV1(MAX_TOUR_SELLER_BCI));
                    fifo.setActionAmountFirstType(vagueService.convertBciBanV1(MAX_TOUR_SELLER_BCI));
                    fifo.setActionAmountSecondType(MAX_TOUR_SELLER_BCI);
                    fifo.setReferencToken(referencTokenTour);
                    fifo.setAmount(MAX_TOUR_SELLER_BCI);
                    fifoRepository.save(fifo);
                    tourService.addTour(fifo);
                    Fifo fifo1 = new Fifo();
                    fifo1.setKsu(fifo.getKsu());
                    fifo1.setDefaultAmountSecondType(Math.abs(difference));
                    fifo1.setDefaultAmountFirstType(vagueService.convertBciBanV1(Math.abs(difference)));
                    fifo1.setActionAmountFirstType(vagueService.convertBciBanV1(Math.abs(difference)));
                    fifo1.setActionAmountSecondType(Math.abs(difference));
                    fifo1.setKsuType(fifo.getKsuType());
                    fifo1.setAmount(Math.abs(difference));
                    fifo1.setPanierTour(true);
                    fifo1.setReferencToken(referencTokenBasket);
                    fifo1.setAvr(fifo.getAvr());
                    fifo1.setVague(fifo.getVague());
                    fifo1.setDetailAgr(fifo.getDetailAgr());
                    fifoRepository.save(fifo1);
                    basketService.addOrRemoveBasket(fifo1, true);
                }
            } else
                if (fifo.getKsuType() == KsuType.BUYER) {
                double secondMaxValueBci = vagueService.convertBanBciV1(fifo.getAmount());
                double difference = MAX_TOUR_BUYER_BAN - fifo.getAmount();
                if (MAX_TOUR_BUYER_BAN >= fifo.getAmount()) {
                    fifo.setDefaultAmountSecondType(secondMaxValueBci);
                    fifo.setDefaultAmountFirstType(fifo.getDefaultAmountFirstType());
                    fifo.setActionAmountFirstType(fifo.getActionAmountFirstType());
                    fifo.setActionAmountSecondType(secondMaxValueBci);
                    fifoRepository.save(fifo);
                    tourService.addTour(fifo);
                } else {
                    fifo.setDefaultAmountSecondType(vagueService.convertBciBanV1(MAX_TOUR_BUYER_BAN));
                    fifo.setDefaultAmountFirstType(MAX_TOUR_BUYER_BAN);
                    fifo.setActionAmountFirstType(MAX_TOUR_BUYER_BAN);
                    fifo.setActionAmountSecondType(vagueService.convertBciBanV1(MAX_TOUR_BUYER_BAN));
                    fifo.setAmount(MAX_TOUR_BUYER_BAN);
                    fifoRepository.save(fifo);
                    tourService.addTour(fifo);
                    Fifo fifo1 = new Fifo();
                    fifo1.setKsu(fifo.getKsu());
                    fifo1.setDefaultAmountSecondType(-1 * vagueService.convertBciBanV1(difference));
                    fifo1.setDefaultAmountFirstType(-1 * difference);
                    fifo1.setActionAmountFirstType(-1 * difference);
                    fifo1.setActionAmountSecondType(-1 * vagueService.convertBciBanV1(difference));
                    fifo1.setKsuType(fifo.getKsuType());
                    fifo1.setKsu(fifo.getKsu());
                    fifo1.setPanierTour(true);
                    fifo1.setDetailAgr(fifo.getDetailAgr());
                    fifo1.setAmount(-1 * difference);
                    fifoRepository.save(fifo1);
                    basketService.addOrRemoveBasket(fifo1, true);
                }
            }
        }
    }

    public void manageTourAchat(Fifo fifo) throws Exception {
        double MAX_TOUR_SELLER_BCI = Double.parseDouble(settingsService.getByCode("PARAM_MAX_TOUR_SELLER_BCI").getValue());
        double MAX_TOUR_BUYER_BAN = Double.parseDouble(settingsService.getByCode("PARAM_MAX_TOUR_BUYER_BAN").getValue());
        if (fifo.getActionAmountSecondType() == 0) {
            throw new Exception("Amount Null");
        } else {
            if (fifo.getKsuType() == KsuType.SELLER) {
                double secondMaxValueBan = fifo.getActionAmountFirstType();
                double difference = MAX_TOUR_SELLER_BCI - fifo.getActionAmountSecondType();
                if (MAX_TOUR_SELLER_BCI >= fifo.getActionAmountSecondType()) {
                    fifo.setDefaultAmountSecondType(fifo.getDefaultAmountSecondType());
                    fifo.setDefaultAmountFirstType(secondMaxValueBan);
                    fifo.setActionAmountFirstType(secondMaxValueBan);
                    fifo.setActionAmountSecondType(fifo.getDefaultAmountSecondType());
                    fifoRepository.save(fifo);
                    tourService.addTour(fifo);
                } else {
                    fifo.setDefaultAmountSecondType(MAX_TOUR_SELLER_BCI);
                    fifo.setDefaultAmountFirstType(vagueService.convertBciBanV1(MAX_TOUR_SELLER_BCI));
                    fifo.setActionAmountFirstType(vagueService.convertBciBanV1(MAX_TOUR_SELLER_BCI));
                    fifo.setActionAmountSecondType(MAX_TOUR_SELLER_BCI);
                    fifo.setAmount(MAX_TOUR_SELLER_BCI);
                    fifoRepository.save(fifo);
                    tourService.addTour(fifo);
                    Fifo fifo1 = new Fifo();
                    fifo1.setKsu(fifo.getKsu());
                    fifo1.setDefaultAmountSecondType(-1 * difference);
                    fifo1.setDefaultAmountFirstType(-1 * vagueService.convertBciBanV1(difference));
                    fifo1.setActionAmountFirstType(-1 * vagueService.convertBciBanV1(difference));
                    fifo1.setActionAmountSecondType(-1 * difference);
                    fifo1.setKsuType(fifo.getKsuType());
                    fifo1.setKsu(fifo.getKsu());
                    fifo1.setAmount(-1 * difference);
                    fifoRepository.save(fifo1);
                    basketService.addOrRemoveBasket(fifo1, true);
                }
            } else
                if (fifo.getKsuType() == KsuType.BUYER) {
                double secondMaxValueBci = vagueService.convertBanBciV1(fifo.getDefaultAmountFirstType());
                double difference = MAX_TOUR_BUYER_BAN - fifo.getDefaultAmountFirstType();
                if (MAX_TOUR_BUYER_BAN >= fifo.getDefaultAmountFirstType()) {
                    fifo.setDefaultAmountSecondType(secondMaxValueBci);
                    fifo.setDefaultAmountFirstType(fifo.getDefaultAmountFirstType());
                    fifo.setActionAmountFirstType(fifo.getActionAmountFirstType());
                    fifo.setActionAmountSecondType(secondMaxValueBci);
                    fifoRepository.save(fifo);
                    tourService.addTour(fifo);

                } else {
                    fifo.setDefaultAmountSecondType(vagueService.convertBanBciV1(MAX_TOUR_BUYER_BAN));
                    fifo.setDefaultAmountFirstType(MAX_TOUR_BUYER_BAN);
                    fifo.setActionAmountFirstType(MAX_TOUR_BUYER_BAN);
                    fifo.setActionAmountSecondType(vagueService.convertBanBciV1(MAX_TOUR_BUYER_BAN));
                    fifo.setAmount(MAX_TOUR_BUYER_BAN);
                    fifoRepository.save(fifo);
                    tourService.addTour(fifo);
                    Fifo fifo1 = new Fifo();

                    fifo1.setKsu(fifo.getKsu());
                    fifo1.setDefaultAmountSecondType(-1 * vagueService.convertBanBciV1(difference));
                    fifo1.setDefaultAmountFirstType(-1 * difference);
                    fifo1.setActionAmountFirstType(-1 * difference);
                    fifo1.setActionAmountSecondType(-1 * vagueService.convertBanBciV1(difference));
                    fifo1.setKsuType(fifo.getKsuType());
                    fifo1.setAmount(-1 * difference);
                    fifo1.setAvr(fifo.getAvr());
                    fifo1.setKsu(fifo.getKsu());
                    fifo1.setPanierTour(true);
                    fifo1.setDetailAgr(fifo.getDetailAgr());
                    fifo1.setVague(fifo.getVague());
                    fifoRepository.save(fifo1);
                    basketService.addOrRemoveBasket(fifo1, true);
                }
            }
        }
    }
}

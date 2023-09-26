package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.repositories.FifoAdminRepository;
import com.esmc.gestionAvr.utils.enums.KsuType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class FifoAdminImpl implements FifoAdminService{
    private final FifoAdminRepository fifoAdminRepository;
    private final SettingsService settingsService;

    @Override
    public int calculNumberTour(double amount, KsuType ksuType){
        int x = 0;
       if(ksuType == KsuType.SELLER){
           double valueAmount = Double.parseDouble(settingsService.getByCode("PARAM_MAX_TOUR_SELLER_BCI").getValue());
            x = (int) (amount / valueAmount);
       } else if (ksuType == KsuType.BUYER) {
           double valueAmount = Double.parseDouble(settingsService.getByCode("PARAM_MAX_TOUR_BUYER_BAN").getValue());
            x = (int) (amount / valueAmount);
       }
       return x;

    }
}
